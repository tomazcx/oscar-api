package org.ufpr.oscarapi.dto;

public record VotoDetalhadoResponse(FilmeInfo filme, DiretorInfo diretor) {

    public record FilmeInfo(Long id, String nome, String genero, String foto) {}

    public record DiretorInfo(Long id, String nome) {}
}
