package com.compass;

import com.compass.domain.enums.Sexo;
import com.compass.infrastructure.controllers.AbrigoController;
import com.compass.domain.Abrigo;
import com.compass.domain.Pessoa;
import com.compass.infrastructure.AbrigoRepositoryJPA;
import com.compass.application.usecases.GerenciarAbrigo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AbrigoRepositoryJPA abrigoRepository = new AbrigoRepositoryJPA();
        GerenciarAbrigo gerenciarAbrigo = new GerenciarAbrigo(abrigoRepository);
        AbrigoController abrigoController = new AbrigoController(gerenciarAbrigo);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cadastrar abrigo");
            System.out.println("2. Registrar entrada de pessoa");
            System.out.println("3. Listar pessoas no abrigo");
            System.out.println("4. Listar pessoas no abrigo por faixa etária e sexo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome do abrigo: ");
                    String nomeAbrigo = scanner.nextLine();
                    abrigoController.cadastrarAbrigo(nomeAbrigo);
                    System.out.println("Abrigo cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("ID do abrigo: ");
                    Long abrigoId = scanner.nextLong();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.print("Nome da pessoa: ");
                    String nomePessoa = scanner.nextLine();
                    System.out.print("Idade da pessoa: ");
                    int idadePessoa = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("Sexo da pessoa (MASCULINO, FEMININO, OUTRO): ");
                    String sexoPessoaSaidaInput = scanner.nextLine().toUpperCase();

                    try {
                        Sexo sexoPessoa = Sexo.valueOf(sexoPessoaSaidaInput);
                        Pessoa pessoa = new Pessoa(nomePessoa, idadePessoa, sexoPessoa);
                        System.out.println("A pessoa já saiu do abrigo? (S/N)");
                        String escolha = scanner.nextLine();

                        if(escolha.equals("S")) {
                            abrigoController.entradaPessoa(abrigoId, pessoa);
                            abrigoController.saidaPessoa(abrigoId, pessoa);
                            System.out.println("Entrada e saída registrada com sucesso!");
                        } else {
                            abrigoController.entradaPessoa(abrigoId, pessoa);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Sexo inválido. Use MASCULINO, FEMININO ou OUTRO.");
                    }


                    break;
                case 3:
                    System.out.print("ID do abrigo: ");
                    Long abrigoIdList = scanner.nextLong();
                    List<Pessoa> pessoas = abrigoController.listarPessoasNoAbrigo(abrigoIdList);
                    if (pessoas != null) {
                        for (Pessoa p : pessoas) {
                            System.out.println("Nome: " + p.getNome() + ", Idade: " + p.getIdade() + ", Sexo: " + p.getSexo());
                        }
                    } else {
                        System.out.println("Abrigo não encontrado!");
                    }
                    break;

                case 4:
                    System.out.print("ID do abrigo: ");
                    Long abrigoIdPopulacao = scanner.nextLong();
                    abrigoController.listarPessoasNoAbrigoPorIdadeSexo(abrigoIdPopulacao);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
