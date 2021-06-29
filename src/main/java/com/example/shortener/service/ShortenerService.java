package com.example.shortener.service;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.dao.ShortenerRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortenerService {

    @Autowired
    private ShortenerRepositoryAdapter shortenerRepositoryAdapter;

    public ShortenerEntity save(ShortenerEntity shortenerEntity) {
        return shortenerRepositoryAdapter.save(shortenerEntity);
    }

    public List<ShortenerEntity> getAll() {
        return shortenerRepositoryAdapter.getAll();
    }

    public Optional<ShortenerEntity> get(String urlKey) {
        return shortenerRepositoryAdapter.get(urlKey);
    }
}
