package br.com.mercadomedieval.mercado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadomedieval.mercado.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);

    List<Personagem> findByClasse(String classe);
}
