package org.ufpr.oscarapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ufpr.oscarapi.dto.ApiResponse;
import org.ufpr.oscarapi.dto.ConfirmarVotoRequest;
import org.ufpr.oscarapi.dto.VotoDetalhadoResponse;
import org.ufpr.oscarapi.repository.UsuarioRepository;
import org.ufpr.oscarapi.repository.VotoRepository;

@RestController
@RequestMapping("/votos")
@Tag(name = "Votação")
@SecurityRequirement(name = "bearerAuth")
public class VotoController {

    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;

    public VotoController(VotoRepository votoRepository, UsuarioRepository usuarioRepository) {
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/meu")
    @Operation(summary = "Retorna o voto confirmado do usuário autenticado")
    public ResponseEntity<VotoDetalhadoResponse> meuVoto(@AuthenticationPrincipal String login) {
        var usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado"));

        return votoRepository.findDetalhadoByUsuarioId(usuario.id())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/confirmar")
    @Operation(summary = "Confirma o voto do usuário autenticado")
    public ResponseEntity<ApiResponse> confirmar(
            @AuthenticationPrincipal String login,
            @Valid @RequestBody ConfirmarVotoRequest request
    ) {
        var usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado"));

        if (votoRepository.existsByUsuarioId(usuario.id())) {
            return ResponseEntity.status(409)
                    .body(new ApiResponse(false, "Voto já registrado para este usuário"));
        }

        if (usuario.tokenVotacao().intValue() != request.tokenVotacao().intValue()) {
            return ResponseEntity.status(403)
                    .body(new ApiResponse(false, "Token de votação inválido"));
        }

        votoRepository.save(usuario.id(), request.filmeId(), request.diretorId());
        return ResponseEntity.ok(new ApiResponse(true, "Voto confirmado com sucesso"));
    }
}
