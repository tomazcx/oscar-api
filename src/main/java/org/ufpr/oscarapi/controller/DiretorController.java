package org.ufpr.oscarapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ufpr.oscarapi.model.Diretor;
import org.ufpr.oscarapi.repository.DiretorRepository;

import java.util.List;

@RestController
@RequestMapping("/diretores")
@Tag(name = "Diretores")
@SecurityRequirement(name = "bearerAuth")
public class DiretorController {

    private final DiretorRepository diretorRepository;

    public DiretorController(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os diretores indicados")
    public List<Diretor> listar() {
        return diretorRepository.findAll();
    }
}
