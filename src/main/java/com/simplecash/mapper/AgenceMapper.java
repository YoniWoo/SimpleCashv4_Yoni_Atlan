package com.simplecash.mapper;

import com.simplecash.dto.AgenceCreateDTO;
import com.simplecash.dto.AgenceDTO;
import com.simplecash.dto.AgenceUpdateDTO;
import com.simplecash.entity.Agence;
import org.springframework.stereotype.Component;

@Component
public class AgenceMapper {

    // Entity -> DTO
    public AgenceDTO toDto(Agence agence) {
        return new AgenceDTO(
                agence.getId(),
                agence.getDateCreation()
        );
    }

    // CreateDto -> Entity
    public Agence toEntity(AgenceCreateDTO dto) {
        return Agence.builder()
                .id(dto.id())
                .dateCreation(dto.dateCreation())
                .build();
    }

    // UpdateDto -> applique sur une entité existante
    public void updateEntity(Agence entity, AgenceUpdateDTO dto) {
        if (dto.dateCreation() != null) {
            entity.setDateCreation(dto.dateCreation());
        }
    }
}
