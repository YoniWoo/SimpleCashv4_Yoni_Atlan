package com.simplecash.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CompteCreateDTO(
        @NotBlank(message = "Le numéro de compte est obligatoire")
        String numero,

        String typeCompte,

        // optionnel : si null, on mettra 0 dans le service
        BigDecimal solde,

        // optionnel : si null, on mettra la date du jour dans le service
        LocalDate dateOuverture
) {
}
