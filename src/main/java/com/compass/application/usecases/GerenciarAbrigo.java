package com.compass.application.usecases;

import com.compass.domain.Abrigo;
import com.compass.domain.Pessoa;
import com.compass.domain.repository.AbrigoRepository;

import java.util.List;

public class GerenciarAbrigo {
    private final AbrigoRepository abrigoRepository;

    public GerenciarAbrigo(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    public void registrarEntrada(Long abrigoId, Pessoa pessoa) {
        Abrigo abrigo = abrigoRepository.findById(abrigoId);
        if (abrigo != null) {
            abrigo.entrada(pessoa);
            abrigoRepository.save(abrigo);
        } else {
            // Tratamento de erro: abrigo não encontrado
            System.out.println("Abrigo não encontrado!");
        }
    }

    public void registrarSaida(Long abrigoId, Pessoa pessoa) {
        Abrigo abrigo = abrigoRepository.findById(abrigoId);
        if (abrigo != null) {

            abrigo.saida(pessoa);
            abrigoRepository.save(abrigo);
        } else {
            // Tratamento de erro: abrigo não encontrado
            System.out.println("Abrigo não encontrado!");
        }
    }

    public List<Pessoa> listarPessoasNoAbrigo(Long abrigoId) {
        Abrigo abrigo = abrigoRepository.findById(abrigoId);
        System.out.println(abrigo.toDict());
        if (abrigo != null) {
            return abrigo.listarPessoasNoAbrigo();
        } else {
            // Tratamento de erro: abrigo não encontrado
            System.out.println("Abrigo não encontrado!");
            return null;
        }
    }
}
