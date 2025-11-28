package com.simplecash.dto;

// Tous les champs optionnels : null = pas modifié
public record GerantUpdateDTO(
        String nom,
        String prenom,
        String agenceId
) {
}
