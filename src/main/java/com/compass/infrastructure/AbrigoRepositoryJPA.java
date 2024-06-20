package com.compass.infrastructure;

import com.compass.domain.Abrigo;
import com.compass.domain.Pessoa;
import com.compass.domain.repository.AbrigoRepository;
import com.compass.infrastructure.entity.AbrigoEntity;
import com.compass.infrastructure.entity.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.stream.Collectors;

public class AbrigoRepositoryJPA implements AbrigoRepository {
    private final SessionFactory sessionFactory;

    public AbrigoRepositoryJPA() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Abrigo findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AbrigoEntity abrigoEntity = session.get(AbrigoEntity.class, id);
            if (abrigoEntity != null) {
                // Força a inicialização da coleção de pessoas
                abrigoEntity.getPessoas().size();

                Abrigo abrigo = new Abrigo(abrigoEntity.getNomeAbrigo());
                abrigo.setPessoas(abrigoEntity.getPessoas().stream()
                        .map(pe -> new Pessoa(pe.getNome(), pe.getIdade(), pe.getSexo()))
                        .collect(Collectors.toList()));

                session.getTransaction().commit();
                return abrigo;
            } else {
                session.getTransaction().commit();
                return null;
            }
        }
    }
    @Override
    public void save(Abrigo abrigo) {
        System.out.println(abrigo.toDict());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            AbrigoEntity abrigoEntity = new AbrigoEntity(abrigo.getNomeAbrigo());
            abrigoEntity.getPessoas().addAll(
                    abrigo.getPessoas().stream()
                            .map(p -> new PessoaEntity(p.getNome(), p.getIdade(), p.getSexo()))
                            .collect(Collectors.toList())
            );

            session.merge(abrigoEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Abrigo findByName(String name) {
        return null;
    }
}
