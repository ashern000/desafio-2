package com.compass.infrastructure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abrigo")
@Data
@NoArgsConstructor
public class AbrigoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAbrigo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PessoaEntity> pessoas = new ArrayList<>();

    public AbrigoEntity(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }
}
