package com.simplecash.service.impl;

import com.simplecash.dto.*;
import com.simplecash.entity.Agence;
import com.simplecash.entity.Conseiller;
import com.simplecash.entity.Gerant;
import com.simplecash.mapper.ConseillerMapper;
import com.simplecash.mapper.GerantMapper;
import com.simplecash.repository.ConseillerRepository;
import com.simplecash.repository.GerantRepository;
import com.simplecash.repository.AgenceRepository;
import com.simplecash.service.GerantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GerantServiceImpl implements GerantService {

    private final GerantRepository gerantRepository;
    private final AgenceRepository agenceRepository;
    private final ConseillerRepository conseillerRepository;
    private final GerantMapper gerantMapper;
    private final ConseillerMapper conseillerMapper;

    @Override
    public List<GerantDTO> getAll() {
        return gerantRepository.findAll()
                .stream()
                .map(gerantMapper::toDto)
                .toList();
    }

    @Override
    public GerantDTO create(GerantCreateDTO dto) {
        Gerant gerant = gerantMapper.toEntity(dto);

        if (dto.agenceId() != null) {
            Agence agence = agenceRepository.findById(dto.agenceId())
                    .orElseThrow(() -> new RuntimeException("Agence introuvable pour id " + dto.agenceId()));
            gerantMapper.setAgence(gerant, agence);
        }

        Gerant saved = gerantRepository.save(gerant);
        return gerantMapper.toDto(saved);
    }

    @Override
    public Optional<GerantDTO> getById(Long id) {
        return gerantRepository.findById(id)
                .map(gerantMapper::toDto);
    }

    @Override
    public Optional<GerantDTO> update(Long id, GerantUpdateDTO dto) {
        return gerantRepository.findById(id)
                .map(existing -> {
                    gerantMapper.updateEntity(existing, dto);
                    if (dto.agenceId() != null) {
                        Agence agence = agenceRepository.findById(dto.agenceId())
                                .orElseThrow(() -> new RuntimeException("Agence introuvable pour id " + dto.agenceId()));
                        gerantMapper.setAgence(existing, agence);
                    }
                    return gerantMapper.toDto(existing);
                });
    }

    @Override
    public void delete(Long id) {
        gerantRepository.deleteById(id);
    }

    @Override
    public ConseillerDTO addConseiller(Long gerantId, ConseillerCreateDTO dto) {
        // 1. Récupérer le gérant
        Gerant gerant = gerantRepository.findById(gerantId)
                .orElseThrow(() -> new RuntimeException("Gérant introuvable avec id " + gerantId));

        // 2. Vérifier qu'il a une agence
        Agence agence = gerant.getAgence();
        if (agence == null) {
            throw new RuntimeException("Le gérant avec id " + gerantId + " n'est lié à aucune agence");
        }

        // 3. Mapper le conseiller à partir du DTO (sans agence)
        Conseiller conseiller = conseillerMapper.toEntity(dto);

        // 4. Associer l'agence du gérant au conseiller
        conseillerMapper.setAgence(conseiller, agence);

        // 5. Sauvegarder et renvoyer le DTO
        Conseiller saved = conseillerRepository.save(conseiller);
        return conseillerMapper.toDto(saved);
    }
}
