package com.compass.domain;

// Produto.java

public class Produto {
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void removerQuantidade(int quantidadeRemovida) {
        if (quantidadeRemovida > this.quantidade) {
            throw new IllegalArgumentException("Quantidade a ser removida é maior que a quantidade em estoque");
        }
        this.quantidade -= quantidadeRemovida;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + ", Quantidade em estoque: " + quantidade;
    }
}
