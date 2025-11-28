package com.simplecash.mapper;

import com.simplecash.dto.CompteCreateDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.dto.CompteUpdateDTO;
import com.simplecash.entity.Client;
import com.simplecash.entity.Compte;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    // Entity -> DTO
    public CompteDTO toDto(Compte compte) {
        Long clientId = compte.getClient() != null
                ? compte.getClient().getId()
                : null;

        return new CompteDTO(
                compte.getId(),
                compte.getNumero(),
                compte.getSolde(),
                compte.getDateOuverture(),
                compte.getTypeCompte(),
                clientId
        );
    }

    // CreateDto -> Entity (sans le client)
    public Compte toEntity(CompteCreateDTO dto) {
        return Compte.builder()
                .numero(dto.numero())
                .solde(dto.solde())
                .dateOuverture(dto.dateOuverture())
                .typeCompte(dto.typeCompte())
                .build();
    }

    // UpdateDto -> applique sur une entité existante
    public void updateEntity(Compte entity, CompteUpdateDTO dto) {
        if (dto.numero() != null) {
            entity.setNumero(dto.numero());
        }
        if (dto.typeCompte() != null) {
            entity.setTypeCompte(dto.typeCompte());
        }
        // on ne touche pas au client, ni au solde ici
    }

    public void setClient(Compte compte, Client client) {
        compte.setClient(client);
    }
}
