package org.ufpr.oscarapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ufpr.oscarapi.model.Diretor;

import java.util.List;

@Repository
public class DiretorRepository {

    private final JdbcTemplate jdbc;

    public DiretorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Diretor> findAll() {
        return jdbc.query(
                "SELECT id, nome FROM diretores ORDER BY id",
                (rs, row) -> new Diretor(rs.getLong("id"), rs.getString("nome"))
        );
    }
}
