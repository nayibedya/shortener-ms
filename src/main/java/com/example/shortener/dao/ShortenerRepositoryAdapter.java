package com.example.shortener.dao;

import com.example.shortener.Entity.ShortenerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShortenerRepositoryAdapter {

    @Autowired
    private ShortenerJpaRepository shortenerJpaRepository;

    public ShortenerEntity save(ShortenerEntity shortenerEntity) {
        return shortenerJpaRepository.save(shortenerEntity);
    }
}
