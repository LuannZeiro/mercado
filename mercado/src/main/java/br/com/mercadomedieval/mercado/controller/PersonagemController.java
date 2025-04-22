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

import br.com.mercadomedieval.mercado.model.Personagem;
import br.com.mercadomedieval.mercado.repository.PersonagemRepository;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemRepository repo;

    public PersonagemController(PersonagemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Personagem> listarTodos(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String classe) {
        if (nome != null)
            return repo.findByNomeContainingIgnoreCase(nome);
        if (classe != null)
            return repo.findByClasse(classe);
        return repo.findAll();
    }

    @PostMapping
    public Personagem criar(@RequestBody Personagem p) {
        if (p.getNivel() < 1 || p.getNivel() > 99) {
            throw new IllegalArgumentException("Nível deve estar entre 1 e 99");
        }
        return repo.save(p);
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody Personagem p) {
        p.setId(id);
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
