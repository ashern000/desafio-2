package com.compass.infrastructure.controllers;

import com.compass.application.usecases.GerenciarEstoque;
import com.compass.domain.Estoque;
import com.compass.domain.Produto;

public class EstoqueController {
    private final GerenciarEstoque gerenciarEstoque;

    public EstoqueController(GerenciarEstoque gerenciarEstoque) {
        this.gerenciarEstoque = gerenciarEstoque;
    }

    public Estoque buscarEstoquePorId(Long id) {
        return gerenciarEstoque.buscarEstoquePorId(id);
    }

    public void salvarEstoque(Estoque estoque) {
        gerenciarEstoque.salvarEstoque(estoque);
    }

    public Estoque buscarEstoquePorNome(String nome) {
        return gerenciarEstoque.buscarEstoquePorNome(nome);
    }

    public void inserirProdutoNoEstoque(Produto produto, Long idEstoque) {
        gerenciarEstoque.inserirProdutoNoEstoque(produto, idEstoque);
    }

    public void atualizarProdutoNoEstoque(Produto produto, Long idEstoque) {
        gerenciarEstoque.atualizarProdutoNoEstoque(produto, idEstoque);
    }
}

