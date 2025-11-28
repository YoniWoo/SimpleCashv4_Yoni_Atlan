package com.simplecash.dto;

import java.time.LocalDate;

// Tous les champs optionnels : null = pas modifié
public record AgenceUpdateDTO(
        LocalDate dateCreation
) {
}
