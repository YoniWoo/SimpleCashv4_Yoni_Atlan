package com.simplecash.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record VirementDTO(
        @NotNull(message = "Le compte source est obligatoire")
        Long compteSourceId,

        @NotNull(message = "Le compte destination est obligatoire")
        Long compteDestinationId,

        @NotNull(message = "Le montant est obligatoire")
        BigDecimal montant
) {
}
