package com.simplecash.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateDTO(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        String adresse,
        String codePostal,
        String ville,
        String telephone,

        @NotBlank(message = "Le type client est obligatoire")
        String typeClient,

        Long conseillerId
) {
}
