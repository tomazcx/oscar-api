package org.ufpr.oscarapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ufpr.oscarapi.model.Usuario;

import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<Usuario> findByLogin(String login) {
        var result = jdbc.query(
                "SELECT id, login, senha FROM usuarios WHERE login = ?",
                (rs, row) -> new Usuario(rs.getLong("id"), rs.getString("login"), rs.getString("senha")),
                login
        );
        return result.stream().findFirst();
    }
}
