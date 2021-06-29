package com.example.shortener.service;

import com.example.shortener.dao.ShortenerRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShortenerServiceTest {

    @InjectMocks
    private ShortenerService shortenerService;

    @Mock
    private ShortenerRepositoryAdapter shortenerRepositoryAdapter;

    @Test
    void should_call_dao_method_once_per_method_call() {
        shortenerService.save(any());
        verify(shortenerRepositoryAdapter, times(1)).save(any());
    }
}
