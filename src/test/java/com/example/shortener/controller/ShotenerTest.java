package com.example.shortener.controller;

import com.example.shortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShotenerTest {

    @InjectMocks
    private Shortener shortener;

    @Mock
    private ShortenerService shortenerService;


    @Test
    void should_generate_shortener_url_and_store() {
        ResponseEntity<?> responseEntity = shortener.generate("http://www.short.com");
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
}
