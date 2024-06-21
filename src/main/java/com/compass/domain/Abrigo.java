package com.compass.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Abrigo extends EntityDomain{
    private String nomeAbrigo;

    private List<Pessoa> pessoas;

    public Abrigo(String nomeAbrigo) {
        this.pessoas = new ArrayList<>();
        this.nomeAbrigo = nomeAbrigo;
    }

    public void entrada(Pessoa pessoa) {
        pessoa.registrarEntrada();
        this.pessoas.add(pessoa);
    }

    public void saida(Pessoa pessoa) {
        pessoa.registrarSaida();
        this.pessoas.add(pessoa);
    }

    public List<Pessoa> listarPessoasNoAbrigo() {
        return this.pessoas.stream()
                .filter(pessoa -> pessoa.getDataHoraSaida() == null)
                .collect(Collectors.toList());
    }

    public String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString () {
        return "Abrigo: " + nomeAbrigo;
    }
}
