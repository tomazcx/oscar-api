package org.ufpr.oscarapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ufpr.oscarapi.model.Filme;

import java.util.List;

@Repository
public class FilmeRepository {

    private final JdbcTemplate jdbc;

    public FilmeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Filme> findAll() {
        return jdbc.query(
                "SELECT id, nome, genero, foto FROM filmes ORDER BY id",
                (rs, row) -> new Filme(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("genero"),
                        rs.getString("foto")
                )
        );
    }
}
