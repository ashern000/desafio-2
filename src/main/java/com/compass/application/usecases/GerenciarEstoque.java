package com.compass.application.usecases;

import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.domain.repository.EstoqueRepository;

public class GerenciarEstoque {
    private final EstoqueRepository estoqueRepository;

    public GerenciarEstoque(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque buscarEstoquePorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public void salvarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public Estoque buscarEstoquePorNome(String nome) {
        return estoqueRepository.findByName(nome);
    }

    public void inserirProdutoNoEstoque(Produto produto, Long idEstoque) {
        estoqueRepository.insertProdutoInEstoque(produto, idEstoque);
    }

    public void atualizarProdutoNoEstoque(Produto produto, Long idEstoque) {
        estoqueRepository.updateProdutoInEstoque(produto, idEstoque);
    }
}
