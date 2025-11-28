package com.simplecash.dto;

import java.time.LocalDate;

public record AgenceDTO(
        String id,
        LocalDate dateCreation
) {
}
