package com.compass.infrastructure.entity;

import com.compass.domain.enums.Sexo;
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
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    public PessoaEntity(String nome, int idade, Sexo sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }
}
