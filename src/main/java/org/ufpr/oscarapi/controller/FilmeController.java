package org.ufpr.oscarapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ufpr.oscarapi.model.Filme;
import org.ufpr.oscarapi.repository.FilmeRepository;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@Tag(name = "Filmes")
@SecurityRequirement(name = "bearerAuth")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os filmes indicados")
    public List<Filme> listar() {
        return filmeRepository.findAll();
    }
}
