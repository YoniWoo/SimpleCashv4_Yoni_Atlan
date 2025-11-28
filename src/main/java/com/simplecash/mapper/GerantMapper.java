package com.simplecash.mapper;

import com.simplecash.dto.GerantCreateDTO;
import com.simplecash.dto.GerantDTO;
import com.simplecash.dto.GerantUpdateDTO;
import com.simplecash.entity.Agence;
import com.simplecash.entity.Gerant;
import org.springframework.stereotype.Component;

@Component
public class GerantMapper {

    // Entity -> DTO
    public GerantDTO toDto(Gerant gerant) {
        String agenceId = gerant.getAgence() != null
                ? gerant.getAgence().getId()
                : null;

        return new GerantDTO(
                gerant.getId(),
                gerant.getNom(),
                gerant.getPrenom(),
                agenceId
        );
    }

    // CreateDto -> Entity (sans agence)
    public Gerant toEntity(GerantCreateDTO dto) {
        return Gerant.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .build();
    }

    // UpdateDto -> applique sur une entité existante
    public void updateEntity(Gerant entity, GerantUpdateDTO dto) {
        if (dto.nom() != null) {
            entity.setNom(dto.nom());
        }
        if (dto.prenom() != null) {
            entity.setPrenom(dto.prenom());
        }
        // agence gérée via service (avec AgenceRepository)
    }

    public void setAgence(Gerant gerant, Agence agence) {
        gerant.setAgence(agence);
    }
}
