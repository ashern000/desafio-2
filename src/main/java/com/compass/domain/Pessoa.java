package com.compass.domain;

import com.compass.domain.enums.Sexo;

import java.time.LocalDateTime;

public class Pessoa extends EntityDomain {
    private String nome;
    private int idade;
    private Sexo sexo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    public Pessoa(String nome, int idade, Sexo sexo) {
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

    public LocalDateTime getDataHoraEntrada() {return dataHoraEntrada;};

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    @Override
    public String toString () {
        return "Pessoa: " + nome + " Sexo: " + sexo + " Saída: " + getDataHoraSaida() + " Entrada:" + dataHoraEntrada;
    }
}

