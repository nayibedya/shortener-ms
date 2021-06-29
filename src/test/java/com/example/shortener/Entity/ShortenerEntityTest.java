package com.example.shortener.Entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.shortener.Entity.ShortenerEntity.buildShortener;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ShortenerEntityTest {

    @InjectMocks
    private ShortenerEntity shortenerEntity;

    @Test
    void should_build_entity () {
        var entity = buildShortener("http://localhost/");
        assertThat(entity.getShortenerKey().length()).isEqualTo(6);
        assertThat((entity.getActualUrl())).isEqualTo("http://localhost/");
        assertThat(entity.getCounter()).isEqualTo(0);
    }
}
