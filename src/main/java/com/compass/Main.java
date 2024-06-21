package com.compass;

import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.domain.enums.Sexo;
import com.compass.infrastructure.controllers.AbrigoController;
import com.compass.domain.Pessoa;
import com.compass.infrastructure.controllers.EstoqueController;
import com.compass.infrastructure.factory.AbrigoFactory;
import com.compass.infrastructure.factory.EstoqueFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Principal:");
            System.out.println("1. Gestão do Abrigo");
            System.out.println("2. Gestão do Estoque");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int mainOption = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (mainOption) {
                case 1:
                    showAbrigoMenu(scanner);
                    break;
                case 2:
                    showEstoqueMenu(scanner);
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void showAbrigoMenu(Scanner scanner) {
        AbrigoController abrigoController = AbrigoFactory.createAbrigoController();

        while (true) {
            System.out.println("Menu do Abrigo:");
            System.out.println("1. Cadastrar abrigo");
            System.out.println("2. Registrar entrada de pessoa");
            System.out.println("3. Listar pessoas no abrigo");
            System.out.println("4. Listar pessoas no abrigo por faixa etária e sexo");
            System.out.println("5. Calcular necessidade de alimentos");
            System.out.println("6. Voltar ao menu principal");
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
                    System.out.print("Sexo da pessoa (MASCULINO/FEMININO): ");
                    String sexoPessoa = scanner.nextLine();

                    Pessoa pessoa = new Pessoa(nomePessoa, idadePessoa, Sexo.valueOf(sexoPessoa.toUpperCase()));

                    System.out.println("A pessoa já saiu do abrigo? (S/N)");
                    String escolha = scanner.nextLine();

                    if (escolha.equalsIgnoreCase("S")) {
                        abrigoController.entradaPessoa(abrigoId, pessoa);
                        abrigoController.saidaPessoa(abrigoId, pessoa);
                        System.out.println("Entrada e saída registrada com sucesso!");
                    } else {
                        abrigoController.entradaPessoa(abrigoId, pessoa);
                        System.out.println("Entrada registrada com sucesso!");
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
                    Long abrigoIdFaixa = scanner.nextLong();
                    scanner.nextLine();  // Consumir a nova linha
                    abrigoController.listarPessoasNoAbrigoPorIdadeSexo(abrigoIdFaixa);
                    break;
                case 5:
                    System.out.print("ID do abrigo: ");
                    Long abrigoIdCalc = scanner.nextLong();
                    scanner.nextLine();  // Consumir a nova linha
                    abrigoController.necessidadeDeAlimentos(abrigoIdCalc);
                    break;
                case 6:
                    return; // Voltar ao menu principal
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void showEstoqueMenu(Scanner scanner) {
        EstoqueController estoqueController = EstoqueFactory.createEstoqueController();

        while (true) {
            System.out.println("Menu do Estoque:");
            System.out.println("1. Cadastrar estoque");
            System.out.println("2. Adicionar produto ao estoque");
            System.out.println("3. Listar produtos no estoque");
            System.out.println("4. Atualizar produto no estoque");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome do estoque: ");
                    String nomeEstoque = scanner.nextLine();
                    estoqueController.salvarEstoque(new Estoque(nomeEstoque));
                    System.out.println("Estoque cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("ID do estoque: ");
                    Long estoqueId = scanner.nextLong();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Quantidade do produto: ");
                    int quantidadeProduto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    Produto produto = new Produto(nomeProduto, quantidadeProduto);
                    estoqueController.inserirProdutoNoEstoque(produto, estoqueId);
                    System.out.println("Produto adicionado com sucesso!");
                    break;
                case 3:
                    System.out.print("ID do estoque: ");
                    Long estoqueIdList = scanner.nextLong();
                    Estoque estoque = estoqueController.buscarEstoquePorId(estoqueIdList);
                    if (estoque != null) {
                        for (Produto p : estoque.getProdutos()) {
                            System.out.println("Produto: " + p.getNome() + ", Quantidade: " + p.getQuantidade());
                        }
                    } else {
                        System.out.println("Estoque não encontrado!");
                    }
                    break;
                case 4:
                    System.out.print("ID do estoque: ");
                    Long estoqueIdUpdate = scanner.nextLong();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.print("Nome do produto: ");
                    String nomeProdutoUpdate = scanner.nextLine();
                    System.out.print("Nova quantidade do produto: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    Produto produtoUpdate = new Produto(nomeProdutoUpdate, novaQuantidade);
                    estoqueController.atualizarProdutoNoEstoque(produtoUpdate, estoqueIdUpdate);
                    System.out.println("Produto atualizado com sucesso!");
                    break;
                case 5:
                    return; // Voltar ao menu principal
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }



}