package com.compass.infrastructure.controllers;

import com.compass.domain.Pessoa;
import com.compass.application.usecases.GerenciarAbrigo;

import java.util.List;

public class AbrigoController {
    private final GerenciarAbrigo gerenciarAbrigo;

    public AbrigoController(GerenciarAbrigo gerenciarAbrigo) {
        this.gerenciarAbrigo = gerenciarAbrigo;
    }

    public void entradaPessoa(Long abrigoId, Pessoa pessoa) {
        gerenciarAbrigo.registrarEntrada(abrigoId, pessoa);
    }

    public void cadastrarAbrigo(String nomeAbrigo) {
        gerenciarAbrigo.cadastrarAbrigo(nomeAbrigo);
    }

    public void saidaPessoa(Long abrigoId, Pessoa pessoa) {
        gerenciarAbrigo.registrarSaida(abrigoId, pessoa);
    }

    public List<Pessoa> listarPessoasNoAbrigo(Long abrigoId) {
        return gerenciarAbrigo.listarPessoasNoAbrigo(abrigoId);
    }

    public void listarPessoasNoAbrigoPorIdadeSexo(Long abrigoId) {
        gerenciarAbrigo.listarPopulacaoPorFaixaEtariaESexo(abrigoId);
    }

    public void necessidadeDeAlimentos(Long abrigoId) {
        gerenciarAbrigo.calcularNecessidadeDeAlimentos(abrigoId);
    }
}
