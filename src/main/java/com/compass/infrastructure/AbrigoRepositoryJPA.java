package com.compass.infrastructure;

import com.compass.domain.Abrigo;
import com.compass.domain.Pessoa;
import com.compass.domain.enums.Sexo;
import com.compass.domain.repository.AbrigoRepository;
import com.compass.infrastructure.entity.AbrigoEntity;
import com.compass.infrastructure.entity.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Map;
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

    public void insertPeopleInAbrigo(Pessoa pessoa, Long idAbrigo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Obter o AbrigoEntity existente do banco de dados
                AbrigoEntity abrigoEntity = session.get(AbrigoEntity.class, idAbrigo);
                if (abrigoEntity == null) {
                    System.out.println("Abrigo não encontrado!");
                    return;
                }

                // Adicionar a nova pessoa ao abrigo
                PessoaEntity pessoaEntity = new PessoaEntity(pessoa.getNome(), pessoa.getIdade(), pessoa.getSexo());
                pessoaEntity.setDataHoraEntrada(pessoa.getDataHoraEntrada());
                pessoaEntity.setDataHoraSaida(pessoa.getDataHoraSaida());
                abrigoEntity.getPessoas().add(pessoaEntity);

                // Persistir as mudanças no banco de dados
                session.merge(abrigoEntity);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void findForYearAndGender(Long idAbrigo) {
        try (Session session = sessionFactory.openSession()) {
            AbrigoEntity abrigoEntity = session.get(AbrigoEntity.class, idAbrigo);
            if (abrigoEntity != null) {
                Map<String, Map<Sexo, Long>> populacaoAgrupada = abrigoEntity.getPessoas().stream()
                        .collect(Collectors.groupingBy(this::determinarFaixaEtaria,
                                Collectors.groupingBy(PessoaEntity::getSexo, Collectors.counting())));

                System.out.println("População do Abrigo " + abrigoEntity.getNomeAbrigo() + " agrupada por faixa etária e sexo:");
                populacaoAgrupada.forEach((faixaEtaria, grupoPorSexo) -> {
                    System.out.println("Faixa Etária: " + faixaEtaria);
                    grupoPorSexo.forEach((sexo, count) -> {
                        System.out.println("  Sexo: " + sexo + " - Quantidade: " + count);
                    });
                });
            } else {
                System.out.println("Abrigo não encontrado!");
            }
        }
    }

    @Override
    public void updatePeopleInAbrigo(Pessoa pessoa, Long idAbrigo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AbrigoEntity abrigoEntity = session.get(AbrigoEntity.class, idAbrigo);

            if (abrigoEntity != null) {
                for (PessoaEntity pessoaEntity : abrigoEntity.getPessoas()) {
                    if (pessoaEntity.getNome().equals(pessoa.getNome())
                            && pessoaEntity.getIdade() == pessoa.getIdade()
                            && pessoaEntity.getSexo() == pessoa.getSexo()) {

                        pessoaEntity.setDataHoraSaida(pessoa.getDataHoraSaida());
                        session.update(pessoaEntity);
                        break;
                    }
                }
            }

            session.getTransaction().commit();
        }
    }

    private String determinarFaixaEtaria(PessoaEntity pessoa) {
        int idade = pessoa.getIdade();
        if (idade <= 3) return "Crianças até 3 anos";
        if (idade <= 8) return "Crianças de 4 a 8 anos";
        if (idade <= 12) return "Crianças de 9 a 12 anos";
        if (idade <= 18) return "Adolescentes de 13 a 18 anos";
        if (idade <= 59) return "Adultos de 19 a 59 anos";
        return "Idosos a partir de 60 anos";
    }

}
