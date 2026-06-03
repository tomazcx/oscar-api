package org.ufpr.oscarapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner seedUsuarios(
            JdbcTemplate jdbc,
            @Value("${SENHA_ALICE}") String senhaAlice,
            @Value("${SENHA_BOB}") String senhaBob,
            @Value("${SENHA_CAROL}") String senhaCarol,
            @Value("${SENHA_DAVE}") String senhaDave,
            @Value("${SENHA_EVE}") String senhaEve
    ) {
        return args -> {
            jdbc.update("INSERT INTO usuarios (login, senha) VALUES (?, ?) ON CONFLICT (login) DO NOTHING",
                    "alice", senhaAlice);
            jdbc.update("INSERT INTO usuarios (login, senha) VALUES (?, ?) ON CONFLICT (login) DO NOTHING",
                    "bob", senhaBob);
            jdbc.update("INSERT INTO usuarios (login, senha) VALUES (?, ?) ON CONFLICT (login) DO NOTHING",
                    "carol", senhaCarol);
            jdbc.update("INSERT INTO usuarios (login, senha) VALUES (?, ?) ON CONFLICT (login) DO NOTHING",
                    "dave", senhaDave);
            jdbc.update("INSERT INTO usuarios (login, senha) VALUES (?, ?) ON CONFLICT (login) DO NOTHING",
                    "eve", senhaEve);

            // carol tem voto pré-confirmado para demonstração
            jdbc.update("""
                    INSERT INTO votos (usuario_id, filme_id, diretor_id)
                    SELECT u.id, 1, 1 FROM usuarios u WHERE u.login = 'carol'
                    ON CONFLICT (usuario_id) DO NOTHING
                    """);
        };
    }
}
