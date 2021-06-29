package com.example.shortener.service;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.dao.ShortenerRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortenerService {

    @Autowired
    private ShortenerRepositoryAdapter shortenerRepositoryAdapter;

    public ShortenerEntity save(ShortenerEntity shortenerEntity) {
        return shortenerRepositoryAdapter.save(shortenerEntity);
    }

    public List<ShortenerEntity> get() {
        return shortenerRepositoryAdapter.get();
    }
}
