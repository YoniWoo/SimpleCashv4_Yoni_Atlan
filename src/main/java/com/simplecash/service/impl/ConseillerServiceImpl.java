package com.simplecash.service.impl;

import com.simplecash.dto.ConseillerCreateDTO;
import com.simplecash.dto.ConseillerDTO;
import com.simplecash.dto.ConseillerUpdateDTO;
import com.simplecash.entity.Agence;
import com.simplecash.entity.Conseiller;
import com.simplecash.mapper.ConseillerMapper;
import com.simplecash.repository.AgenceRepository;
import com.simplecash.repository.ConseillerRepository;
import com.simplecash.service.ConseillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConseillerServiceImpl implements ConseillerService {

    private final ConseillerRepository conseillerRepository;
    private final AgenceRepository agenceRepository;
    private final ConseillerMapper conseillerMapper;

    @Override
    public List<ConseillerDTO> getAll() {
        return conseillerRepository.findAll()
                .stream()
                .map(conseillerMapper::toDto)
                .toList();
    }

    @Override
    public ConseillerDTO create(ConseillerCreateDTO dto) {
        Conseiller conseiller = conseillerMapper.toEntity(dto);

        if (dto.agenceId() != null) {
            Agence agence = agenceRepository.findById(dto.agenceId())
                    .orElseThrow(() -> new RuntimeException("Agence introuvable pour id " + dto.agenceId()));
            conseillerMapper.setAgence(conseiller, agence);
        }

        Conseiller saved = conseillerRepository.save(conseiller);
        return conseillerMapper.toDto(saved);
    }

    @Override
    public Optional<ConseillerDTO> getById(Long id) {
        return conseillerRepository.findById(id)
                .map(conseillerMapper::toDto);
    }

    @Override
    public Optional<ConseillerDTO> update(Long id, ConseillerUpdateDTO dto) {
        return conseillerRepository.findById(id)
                .map(existing -> {
                    conseillerMapper.updateEntity(existing, dto);
                    if (dto.agenceId() != null) {
                        Agence agence = agenceRepository.findById(dto.agenceId())
                                .orElseThrow(() -> new RuntimeException("Agence introuvable pour id " + dto.agenceId()));
                        conseillerMapper.setAgence(existing, agence);
                    }
                    return conseillerMapper.toDto(existing);
                });
    }

    @Override
    public void delete(Long id) {
        conseillerRepository.deleteById(id);
    }
}
