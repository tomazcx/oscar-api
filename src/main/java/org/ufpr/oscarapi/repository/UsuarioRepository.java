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
                "SELECT id, login, senha, tokenvotacao FROM usuarios WHERE login = ?",
                (rs, row) -> new Usuario(rs.getLong("id"), rs.getString("login"), rs.getString("senha"), rs.getInt("tokenvotacao")),
                login
        );
        return result.stream().findFirst();
    }

    public Usuario update(Usuario usuario) {
        var linhasAfetadas = jdbc.update(
                "UPDATE usuarios SET tokenvotacao = ? WHERE id = ?",
                usuario.tokenVotacao(),
                usuario.id()
                );
        if (linhasAfetadas > 0) {
            return usuario; // Retorna o usuário atualizado se a operação funcionou
        } else {
            throw new RuntimeException("Usuário não encontrado ou atualização falhou");
        }
    }
}
