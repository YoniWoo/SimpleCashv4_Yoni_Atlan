package com.simplecash.service.impl;

import com.simplecash.dto.AgenceCreateDTO;
import com.simplecash.dto.AgenceDTO;
import com.simplecash.dto.AgenceUpdateDTO;
import com.simplecash.entity.Agence;
import com.simplecash.mapper.AgenceMapper;
import com.simplecash.repository.AgenceRepository;
import com.simplecash.service.AgenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public List<AgenceDTO> getAll() {
        return agenceRepository.findAll()
                .stream()
                .map(agenceMapper::toDto)
                .toList();
    }

    @Override
    public AgenceDTO create(AgenceCreateDTO dto) {
        Agence agence = agenceMapper.toEntity(dto);

        if (agence.getDateCreation() == null) {
            agence.setDateCreation(LocalDate.now());
        }

        Agence saved = agenceRepository.save(agence);
        return agenceMapper.toDto(saved);
    }

    @Override
    public Optional<AgenceDTO> getById(String id) {
        return agenceRepository.findById(id)
                .map(agenceMapper::toDto);
    }

    @Override
    public Optional<AgenceDTO> update(String id, AgenceUpdateDTO dto) {
        return agenceRepository.findById(id)
                .map(existing -> {
                    agenceMapper.updateEntity(existing, dto);
                    return agenceMapper.toDto(existing);
                });
    }

    @Override
    public void delete(String id) {
        agenceRepository.deleteById(id);
    }
}
