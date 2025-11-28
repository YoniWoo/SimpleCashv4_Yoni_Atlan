package com.simplecash.dto;

import jakarta.validation.constraints.NotBlank;

public record GerantCreateDTO(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        String agenceId // optionnel : peut être null, on pourra lier plus tard
) {
}
