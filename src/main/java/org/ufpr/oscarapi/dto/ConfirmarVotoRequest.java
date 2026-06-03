package org.ufpr.oscarapi.dto;

import jakarta.validation.constraints.NotNull;

public record ConfirmarVotoRequest(
        @NotNull(message = "filmeId é obrigatório") Integer filmeId,
        @NotNull(message = "diretorId é obrigatório") Integer diretorId
) {}
