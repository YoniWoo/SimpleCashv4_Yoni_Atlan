package com.simplecash.dto;

import jakarta.validation.constraints.NotBlank;

public record ConseillerCreateDTO(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        String agenceId // id de l'agence à rattacher (optionnel)
) {
}
