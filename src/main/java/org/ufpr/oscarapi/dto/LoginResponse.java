package org.ufpr.oscarapi.dto;

public record LoginResponse(boolean sucesso, String token, String mensagem, Integer tokenVotacao) {}
