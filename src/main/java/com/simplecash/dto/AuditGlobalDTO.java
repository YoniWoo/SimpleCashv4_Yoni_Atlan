package com.simplecash.dto;

import java.math.BigDecimal;

public record AuditGlobalDTO(
        long nombreClients,
        long nombreComptes,
        BigDecimal soldeTotal,
        long nbComptesParticuliersAnormaux,
        long nbComptesEntreprisesAnormaux
) {
}
