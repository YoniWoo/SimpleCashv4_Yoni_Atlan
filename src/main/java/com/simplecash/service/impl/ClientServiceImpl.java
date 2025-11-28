package com.simplecash.service.impl;

import com.simplecash.dto.ClientCreateDTO;
import com.simplecash.dto.ClientDTO;
import com.simplecash.dto.ClientUpdateDTO;
import com.simplecash.entity.Client;
import com.simplecash.entity.Conseiller;
import com.simplecash.mapper.ClientMapper;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.ConseillerRepository;
import com.simplecash.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientDTO create(ClientCreateDTO createDto) {
        Client client = clientMapper.toEntity(createDto);

        // gestion du conseiller ici
        if (createDto.conseillerId() != null) {
            Conseiller conseiller = conseillerRepository.findById(createDto.conseillerId())
                    .orElseThrow(() -> new RuntimeException("Conseiller introuvable pour id " + createDto.conseillerId()));
            client.setConseiller(conseiller);
        }

        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    public Optional<ClientDTO> getById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDTO> update(Long id, ClientUpdateDTO updateDto) {
        return clientRepository.findById(id)
                .map(existing -> {
                    // appliquer les champs simples
                    clientMapper.updateEntity(existing, updateDto);

                    // gérer éventuellement le conseiller
                    if (updateDto.conseillerId() != null) {
                        Conseiller conseiller = conseillerRepository.findById(updateDto.conseillerId())
                                .orElseThrow(() -> new RuntimeException("Conseiller introuvable pour id " + updateDto.conseillerId()));
                        existing.setConseiller(conseiller);
                    }

                    return clientMapper.toDto(existing);
                });
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> getByConseiller(Long conseillerId) {
        return clientRepository.findByConseillerId(conseillerId)
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }
}
