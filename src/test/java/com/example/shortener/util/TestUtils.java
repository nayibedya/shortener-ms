package com.example.shortener.util;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.RequestEntity.GenerateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TestUtils {
    public static final String KEY = "CCF54R";

    public static final String URL = "http://localhost/";
    public static final String REQUEST_URL = "http://www.short.com";

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

    public static GenerateRequest buildGenerateRequest(String url) {
        return GenerateRequest.builder().url(url).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
