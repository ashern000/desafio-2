package com.compass.domain.repository;

import com.compass.domain.Abrigo;
import com.compass.domain.Pessoa;

public interface AbrigoRepository {
    Abrigo findById(Long id);
    void save(Abrigo abrigo);
    Abrigo findByName(String name);
    void insertPeopleInAbrigo(Pessoa pessoa, Long idAbrigo);
    void updatePeopleInAbrigo(Pessoa pessoa, Long idAbrigo);
    void findForYearAndGender(Long idALong);
}
