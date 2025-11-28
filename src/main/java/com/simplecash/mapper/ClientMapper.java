package com.simplecash.mapper;

import com.simplecash.dto.ClientCreateDTO;
import com.simplecash.dto.ClientDTO;
import com.simplecash.dto.ClientUpdateDTO;
import com.simplecash.entity.Client;
import com.simplecash.entity.Conseiller;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // Entity -> DTO
    public ClientDTO toDto(Client client) {
        Long conseillerId = client.getConseiller() != null
                ? client.getConseiller().getId()
                : null;

        return new ClientDTO(
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),
                client.getCodePostal(),
                client.getVille(),
                client.getTelephone(),
                client.getTypeClient(),
                conseillerId
        );
    }

    // CreateDto -> Entity (sans le conseiller)
    public Client toEntity(ClientCreateDTO dto) {
        return Client.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .adresse(dto.adresse())
                .codePostal(dto.codePostal())
                .ville(dto.ville())
                .telephone(dto.telephone())
                .typeClient(dto.typeClient())
                .build();
    }

    // UpdateDto -> applique sur une entité existante
    public void updateEntity(Client entity, ClientUpdateDTO dto) {
        if (dto.nom() != null) {
            entity.setNom(dto.nom());
        }
        if (dto.prenom() != null) {
            entity.setPrenom(dto.prenom());
        }
        if (dto.adresse() != null) {
            entity.setAdresse(dto.adresse());
        }
        if (dto.codePostal() != null) {
            entity.setCodePostal(dto.codePostal());
        }
        if (dto.ville() != null) {
            entity.setVille(dto.ville());
        }
        if (dto.telephone() != null) {
            entity.setTelephone(dto.telephone());
        }
        if (dto.typeClient() != null) {
            entity.setTypeClient(dto.typeClient());
        }
        // conseillerId géré dans le service, pas ici
    }

    // Utilitaire si tu veux mapper un Conseiller sur un Client
    public void setConseiller(Client client, Conseiller conseiller) {
        client.setConseiller(conseiller);
    }
}
