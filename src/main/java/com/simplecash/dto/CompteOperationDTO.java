package com.simplecash.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CompteOperationDTO(
        @NotNull(message = "L'id du compte est obligatoire")
        Long compteId,

        @NotNull(message = "Le montant est obligatoire")
        BigDecimal montant
) {
}
