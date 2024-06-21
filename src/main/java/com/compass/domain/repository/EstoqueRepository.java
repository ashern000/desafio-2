package com.compass.domain.repository;

import com.compass.domain.Estoque;
import com.compass.domain.Produto;

public interface EstoqueRepository {
    void save(Estoque estoque);
    Estoque findByName(String name);
    void insertProdutoInEstoque(Produto produto, Long idEstoque);
    public void updateProdutoInEstoque(Produto produto, Long idEstoque);
    Estoque findById(Long id);
}
