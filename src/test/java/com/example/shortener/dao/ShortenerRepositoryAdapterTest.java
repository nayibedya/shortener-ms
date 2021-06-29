package com.example.shortener.dao;

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
public class ShortenerRepositoryAdapterTest {

    @InjectMocks
    private ShortenerRepositoryAdapter shortenerRepositoryAdapter;

    @Mock
    private ShortenerJpaRepository shortenerJpaRepository;

    @Test
    void should_instantiate_jpa_method_once_per_method_call_save() {
        shortenerRepositoryAdapter.save(any());
        verify(shortenerJpaRepository, times(1)).save(any());
    }

    @Test
    void should_instantiate_jpa_method_once_per_method_call_getAll() {
        shortenerRepositoryAdapter.getAll();
        verify(shortenerJpaRepository, times(1)).findAll();
    }

    @Test
    void should_instantiate_jpa_method_once_per_method_call_get() {
        shortenerRepositoryAdapter.get(KEY);
        verify(shortenerJpaRepository, times(1)).findById(any());
    }
}
