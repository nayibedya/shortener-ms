package com.example.shortener.util;

import com.example.shortener.Entity.ShortenerEntity;

import java.util.List;

public class TestUtils {
    public static final String KEY = "CCF54R";

    public static final String URL = "http://localhost/";

    public static ShortenerEntity buildShortenEntity(String url) {
        return ShortenerEntity.builder()
                .shortenerKey(KEY)
                .actualUrl(url)
                .counter(0)
                .build();
    }

    public static List<ShortenerEntity> buildListShortenEntity() {
        return List.of(buildShortenEntity(URL + "1"), buildShortenEntity(URL + "2"));
    }
}
