package com.compass.domain.repository;

import com.compass.domain.Abrigo;

public interface AbrigoRepository {
    Abrigo findById(Long id);
    void save(Abrigo abrigo);
    Abrigo findByName(String name);
}
