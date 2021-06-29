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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        when(shortenerService.save(any())).thenReturn(buildShortenEntity(URL));
        var responseEntity = shortener.generate("http://www.short.com");
        verify(shortenerService, times(1)).save(any());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_validate_url_and_return_response_if_malformedurl_passed() {
        ResponseEntity<?> responseEntity = shortener.generate("http:// www.short.com");
        verify(shortenerService, times(0)).save(any());
        assertThat(responseEntity.getBody()).isEqualTo("Please enter valid url");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void should_get_all_the_shortened_url_list() {
        when((shortenerService.get())).thenReturn(buildListShortenEntity());
        ResponseEntity<List<ShortenerEntity>> response = shortener.get();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(2);
    }

    @Test
    void should_return_blank_list_when_there_is_no_record() {
        when((shortenerService.get())).thenReturn(Collections.emptyList());
        ResponseEntity<List<ShortenerEntity>> response = shortener.get();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(0);
    }
}
