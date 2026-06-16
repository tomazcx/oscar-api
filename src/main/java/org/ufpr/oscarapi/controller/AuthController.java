package org.ufpr.oscarapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ufpr.oscarapi.dto.LoginRequest;
import org.ufpr.oscarapi.dto.LoginResponse;
import org.ufpr.oscarapi.repository.UsuarioRepository;
import org.ufpr.oscarapi.security.JwtUtil;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica o usuário e retorna um JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        return usuarioRepository.findByLogin(request.login())
                .filter(u -> u.senha().equals(request.senha()))
                .map(u -> {
                    String token = jwtUtil.generateToken(u.login());
                    Integer tokenVotacao = ThreadLocalRandom.current().nextInt(0, 101);

                    return ResponseEntity.ok(
                            new LoginResponse(
                                    true,
                                    token,
                                    "Login realizado com sucesso",
                                    tokenVotacao
                            )
                    );
                })
                .orElse(ResponseEntity.status(401)
                        .body(new LoginResponse(
                                false,
                                null,
                                "Login ou senha inválidos",
                                null
                        )));
    }
}
