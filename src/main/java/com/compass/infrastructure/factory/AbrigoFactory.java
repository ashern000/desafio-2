package com.compass.infrastructure.factory;

import com.compass.application.usecases.GerenciarAbrigo;
import com.compass.infrastructure.controllers.AbrigoController;
import com.compass.infrastructure.AbrigoRepositoryJPA;

public class AbrigoFactory {

    public static AbrigoController createAbrigoController() {
        AbrigoRepositoryJPA abrigoRepository = new AbrigoRepositoryJPA();
        GerenciarAbrigo gerenciarAbrigo = new GerenciarAbrigo(abrigoRepository);
        return new AbrigoController(gerenciarAbrigo);
    }
}
