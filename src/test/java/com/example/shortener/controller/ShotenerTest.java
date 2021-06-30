package com.example.shortener.controller;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.shortener.util.TestUtils.KEY;
import static com.example.shortener.util.TestUtils.URL;
import static com.example.shortener.util.TestUtils.buildListShortenEntity;
import static com.example.shortener.util.TestUtils.buildShortenEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ShotenerTest {

    @InjectMocks
    private Shortener shortener;

    @Mock
    private ShortenerService shortenerService;


    @Test
    void should_generate_shortener_url_and_store() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(shortenerService.save(any())).thenReturn(buildShortenEntity(URL));
        var responseEntity = shortener.generate("http://www.short.com");
        verify(shortenerService, times(1)).save(any());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("http://localhost/CCF54R");
    }

    @Test
    void should_validate_url_and_return_response_if_malformedurl_passed() {
        ResponseEntity<?> responseEntity = shortener.generate("http:// www.short.com");
        verify(shortenerService, times(0)).save(any());
        assertThat(responseEntity.getBody()).isEqualTo("Please enter valid url");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_get_all_the_shortened_url_list() {
        when((shortenerService.getAll())).thenReturn(buildListShortenEntity());
        ResponseEntity<List<ShortenerEntity>> response = shortener.getAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(2);
    }

    @Test
    void should_return_blank_list_when_there_is_no_record() {
        when((shortenerService.getAll())).thenReturn(Collections.emptyList());
        ResponseEntity<List<ShortenerEntity>> response = shortener.getAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(0);
    }

    @Test
    void should_redirect_to_original_url_when_called() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(shortenerService.get(any())).thenReturn(Optional.of(buildShortenEntity(URL)));
        shortener.redirect(KEY, response);
        verify(shortenerService, times(1)).get(any());
        assertThat(response.getRedirectedUrl()).isEqualTo(URL);
    }

    @Test
    void should_not_redirect_to_any_url_if_key_not_present() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        shortener.redirect(KEY, response);
        assertThat(response.getRedirectedUrl()).isEqualTo(null);
    }
}
