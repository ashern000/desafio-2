package com.compass.infrastructure.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@Getter
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String sexo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    public PessoaEntity(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }
}
