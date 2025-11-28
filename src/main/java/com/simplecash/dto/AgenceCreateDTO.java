package com.simplecash.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record AgenceCreateDTO(
        @NotBlank(message = "L'id de l'agence est obligatoire")
        String id,

        // optionnel : si null, on mettra la date du jour dans le service
        LocalDate dateCreation
) {
}
