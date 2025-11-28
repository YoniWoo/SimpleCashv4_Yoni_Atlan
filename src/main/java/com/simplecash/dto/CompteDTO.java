package com.simplecash.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CompteDTO(
        Long id,
        String numero,
        BigDecimal solde,
        LocalDate dateOuverture,
        String typeCompte,
        Long clientId
) {
}
