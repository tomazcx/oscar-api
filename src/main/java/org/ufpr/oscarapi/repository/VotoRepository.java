package org.ufpr.oscarapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ufpr.oscarapi.dto.VotoDetalhadoResponse;

import java.util.Optional;

@Repository
public class VotoRepository {

    private final JdbcTemplate jdbc;

    public VotoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean existsByUsuarioId(Long usuarioId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM votos WHERE usuario_id = ?",
                Integer.class,
                usuarioId
        );
        return count != null && count > 0;
    }

    public void save(Long usuarioId, Integer filmeId, Integer diretorId) {
        jdbc.update(
                "INSERT INTO votos (usuario_id, filme_id, diretor_id) VALUES (?, ?, ?)",
                usuarioId, filmeId, diretorId
        );
    }

    public Optional<VotoDetalhadoResponse> findDetalhadoByUsuarioId(Long usuarioId) {
        var result = jdbc.query(
                """
                SELECT f.id   AS filme_id,   f.nome AS filme_nome, f.genero, f.foto,
                       d.id   AS diretor_id, d.nome AS diretor_nome
                FROM votos v
                JOIN filmes   f ON v.filme_id   = f.id
                JOIN diretores d ON v.diretor_id = d.id
                WHERE v.usuario_id = ?
                """,
                (rs, row) -> new VotoDetalhadoResponse(
                        new VotoDetalhadoResponse.FilmeInfo(
                                rs.getLong("filme_id"),
                                rs.getString("filme_nome"),
                                rs.getString("genero"),
                                rs.getString("foto")
                        ),
                        new VotoDetalhadoResponse.DiretorInfo(
                                rs.getLong("diretor_id"),
                                rs.getString("diretor_nome")
                        )
                ),
                usuarioId
        );
        return result.stream().findFirst();
    }
}
