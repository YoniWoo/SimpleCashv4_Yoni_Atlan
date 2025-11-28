package com.simplecash.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConseillerRequestDTO {

    private String nom;
    private String prenom;
    private String agenceId; // id de l'agence à affecter (optionnel ici)
}
