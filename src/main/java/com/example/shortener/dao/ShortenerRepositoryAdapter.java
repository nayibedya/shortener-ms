package com.example.shortener.dao;

import com.example.shortener.Entity.ShortenerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShortenerRepositoryAdapter {

    @Autowired
    private ShortenerJpaRepository shortenerJpaRepository;

    public ShortenerEntity save(ShortenerEntity shortenerEntity) {
        return shortenerJpaRepository.save(shortenerEntity);
    }

    public List<ShortenerEntity> getAll() {
        return shortenerJpaRepository.findAll();
    }

    public Optional<ShortenerEntity> get(String urlKey) {
        return shortenerJpaRepository.findById(urlKey);
    }
}
