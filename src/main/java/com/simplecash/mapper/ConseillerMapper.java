package com.simplecash.mapper;

import com.simplecash.dto.ConseillerCreateDTO;
import com.simplecash.dto.ConseillerDTO;
import com.simplecash.dto.ConseillerUpdateDTO;
import com.simplecash.entity.Agence;
import com.simplecash.entity.Conseiller;
import org.springframework.stereotype.Component;

@Component
public class ConseillerMapper {

    // Entity -> DTO
    public ConseillerDTO toDto(Conseiller conseiller) {
        String agenceId = conseiller.getAgence() != null
                ? conseiller.getAgence().getId()
                : null;

        return new ConseillerDTO(
                conseiller.getId(),
                conseiller.getNom(),
                conseiller.getPrenom(),
                agenceId
        );
    }

    // CreateDto -> Entity (sans agence)
    public Conseiller toEntity(ConseillerCreateDTO dto) {
        return Conseiller.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .build();
    }

    // UpdateDto -> applique sur une entité existante
    public void updateEntity(Conseiller entity, ConseillerUpdateDTO dto) {
        if (dto.nom() != null) {
            entity.setNom(dto.nom());
        }
        if (dto.prenom() != null) {
            entity.setPrenom(dto.prenom());
        }
        // agence gérée dans le service avec AgenceRepository
    }

    public void setAgence(Conseiller conseiller, Agence agence) {
        conseiller.setAgence(agence);
    }
}
