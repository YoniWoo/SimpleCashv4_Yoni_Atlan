package com.simplecash.dto;

public record ClientDTO(
        Long id,
        String nom,
        String prenom,
        String adresse,
        String codePostal,
        String ville,
        String telephone,
        String typeClient,
        Long conseillerId
) {
}
