package com.example.shortener.dao;

import com.example.shortener.Entity.ShortenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerJpaRepository extends JpaRepository<ShortenerEntity, ShortenerEntity> {

}
