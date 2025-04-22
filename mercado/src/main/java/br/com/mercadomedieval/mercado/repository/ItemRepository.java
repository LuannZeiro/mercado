package br.com.mercadomedieval.mercado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadomedieval.mercado.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContainingIgnoreCase(String nome);

    List<Item> findByTipo(String tipo);

    List<Item> findByRaridade(String raridade);

    List<Item> findByPrecoBetween(int precoMin, int precoMax);
}
