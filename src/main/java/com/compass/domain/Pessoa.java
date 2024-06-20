package com.compass.domain;

import java.time.LocalDateTime;

public class Pessoa extends EntityDomain {
    private String nome;
    private int idade;
    private String sexo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    public Pessoa(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public void registrarEntrada() {
        this.dataHoraEntrada = LocalDateTime.now();
    }

    public void registrarSaida() {
        this.dataHoraSaida = LocalDateTime.now();
    }

    public LocalDateTime getDataHoraSaida() { return dataHoraSaida;}

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }
}

