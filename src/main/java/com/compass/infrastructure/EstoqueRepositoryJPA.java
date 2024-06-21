package com.compass.infrastructure;

import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.domain.repository.EstoqueRepository;
import com.compass.infrastructure.entity.EstoqueEntity;
import com.compass.infrastructure.entity.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.stream.Collectors;

public class EstoqueRepositoryJPA implements EstoqueRepository {
    private final SessionFactory sessionFactory;

    public EstoqueRepositoryJPA() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Estoque findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            EstoqueEntity estoqueEntity = session.get(EstoqueEntity.class, id);
            if (estoqueEntity != null) {
                Estoque estoque = new Estoque(estoqueEntity.getNomeEstoque());
                estoque.setProdutos(estoqueEntity.getProdutos().stream()
                        .map(pe -> new Produto(pe.getNome(), pe.getQuantidade()))
                        .collect(Collectors.toList()));

                session.getTransaction().commit();
                return estoque;
            } else {
                session.getTransaction().commit();
                return null;
            }
        }
    }

    @Override
    public void save(Estoque estoque) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            EstoqueEntity estoqueEntity = new EstoqueEntity(estoque.getNomeEstoque());
            estoqueEntity.getProdutos().addAll(
                    estoque.getProdutos().stream()
                            .map(p -> new ProdutoEntity(p.getNome(), p.getQuantidade()))
                            .collect(Collectors.toList())
            );

            session.merge(estoqueEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Estoque findByName(String name) {
        // Implementação pendente
        return null;
    }

    public void insertProdutoInEstoque(Produto produto, Long idEstoque) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                EstoqueEntity estoqueEntity = session.get(EstoqueEntity.class, idEstoque);
                if (estoqueEntity == null) {
                    System.out.println("Estoque não encontrado!");
                    return;
                }

                ProdutoEntity produtoEntity = new ProdutoEntity(produto.getNome(), produto.getQuantidade());
                estoqueEntity.getProdutos().add(produtoEntity);

                session.merge(estoqueEntity);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void updateProdutoInEstoque(Produto produto, Long idEstoque) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            EstoqueEntity estoqueEntity = session.get(EstoqueEntity.class, idEstoque);

            if (estoqueEntity != null) {
                for (ProdutoEntity produtoEntity : estoqueEntity.getProdutos()) {
                    if (produtoEntity.getNome().equals(produto.getNome())) {
                        produtoEntity.setQuantidade(produto.getQuantidade());
                        session.update(produtoEntity);
                        break;
                    }
                }
            }

            session.getTransaction().commit();
        }
    }
}
