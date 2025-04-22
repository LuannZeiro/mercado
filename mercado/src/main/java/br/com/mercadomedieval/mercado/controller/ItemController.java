package br.com.mercadomedieval.mercado.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadomedieval.mercado.model.Item;
import br.com.mercadomedieval.mercado.repository.ItemRepository;
import br.com.mercadomedieval.mercado.repository.PersonagemRepository;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemRepository itemRepo;
    private final PersonagemRepository personagemRepo;

    public ItemController(ItemRepository itemRepo, PersonagemRepository personagemRepo) {
        this.itemRepo = itemRepo;
        this.personagemRepo = personagemRepo;
    }

    @GetMapping
    public List<Item> listar(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String raridade,
            @RequestParam(required = false) Integer precoMin,
            @RequestParam(required = false) Integer precoMax) {
        if (nome != null)
            return itemRepo.findByNomeContainingIgnoreCase(nome);
        if (tipo != null)
            return itemRepo.findByTipo(tipo);
        if (raridade != null)
            return itemRepo.findByRaridade(raridade);
        if (precoMin != null && precoMax != null)
            return itemRepo.findByPrecoBetween(precoMin, precoMax);
        return itemRepo.findAll();
    }

    @PostMapping
    public Item criar(@RequestBody Item item) {
        if (item.getDono() != null && item.getDono().getId() != null) {
            var dono = personagemRepo.findById(item.getDono().getId())
                    .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
            item.setDono(dono);
        } else {
            throw new RuntimeException("Personagem não informado ou ID inválido.");
        }
        return itemRepo.save(item);
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable Long id) {
        return itemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return itemRepo.save(item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        itemRepo.deleteById(id);
    }
}
