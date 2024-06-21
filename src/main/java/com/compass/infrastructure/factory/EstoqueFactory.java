package com.compass.infrastructure.factory;

import com.compass.application.usecases.GerenciarAbrigo;
import com.compass.application.usecases.GerenciarEstoque;
import com.compass.infrastructure.AbrigoRepositoryJPA;
import com.compass.infrastructure.EstoqueRepositoryJPA;
import com.compass.infrastructure.controllers.AbrigoController;
import com.compass.infrastructure.controllers.EstoqueController;

public class EstoqueFactory {
    public static EstoqueController createEstoqueController() {
        EstoqueRepositoryJPA estoqueRepository = new EstoqueRepositoryJPA();
        GerenciarEstoque gerenciarEstoque = new GerenciarEstoque(estoqueRepository);
        return new EstoqueController(gerenciarEstoque);
    }
}
