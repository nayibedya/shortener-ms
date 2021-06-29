package com.example.shortener.service;

import com.example.shortener.dao.ShortenerRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.shortener.util.TestUtils.KEY;
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
    void should_call_dao_method_once_per_method_call_save() {
        shortenerService.save(any());
        verify(shortenerRepositoryAdapter, times(1)).save(any());
    }

    @Test
    void should_call_dao_method_once_per_method_call_getAll() {
        shortenerService.getAll();
        verify(shortenerRepositoryAdapter, times(1)).getAll();
    }

    @Test
    void should_call_dao_method_once_per_method_call_get() {
        shortenerService.get(KEY);
        verify(shortenerRepositoryAdapter, times(1)).get(any());
    }
}
