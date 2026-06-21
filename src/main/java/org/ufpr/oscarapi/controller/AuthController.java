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
import org.ufpr.oscarapi.model.Usuario;
import org.ufpr.oscarapi.repository.UsuarioRepository;
import org.ufpr.oscarapi.security.JwtUtil;

import java.util.Random;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final Random gerador = new Random();

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica o usuário e retorna um JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        var u = usuarioRepository.findByLogin(request.login())
                .filter(u1 -> u1.senha().equals(request.senha()));
        Integer tokenVotacao = gerador.nextInt(101);

        if (u.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(new LoginResponse(
                            false,
                            null,
                            "Login ou senha inválidos",
                            null)
                    );
        }

        var newU = new Usuario(
                u.get().id(),
                u.get().login(),
                u.get().senha(),
                tokenVotacao);

        usuarioRepository.update(newU);


        return ResponseEntity.ok(
                new LoginResponse(
                        true,
                        jwtUtil.generateToken(newU.login()),
                        "Login realizado com sucesso",
                        tokenVotacao
                )
        );
    }
}
