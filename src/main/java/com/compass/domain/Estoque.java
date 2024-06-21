package com.compass.domain;

import java.util.ArrayList;
import java.util.List;

public class Estoque extends EntityDomain {

    private String nomeEstoque;
    private List<Produto> produtos;

    public Estoque(String nomeEstoque) {
        this.nomeEstoque = nomeEstoque;
        this.produtos = new ArrayList<>(); // Inicializa a lista de produtos
    }

    public void adicionarProdutos(Produto produto) {
        this.produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(this.produtos); // Retorna uma cópia da lista de produtos
    }

    // Getters e setters, se necessário
    public String getNomeEstoque() {
        return nomeEstoque;
    }

    public void setNomeEstoque(String nomeEstoque) {
        this.nomeEstoque = nomeEstoque;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
