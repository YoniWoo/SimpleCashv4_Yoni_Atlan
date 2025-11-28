package com.simplecash.service.impl;

import com.simplecash.dto.CompteCreateDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.dto.CompteUpdateDTO;
import com.simplecash.entity.Client;
import com.simplecash.entity.Compte;
import com.simplecash.mapper.CompteMapper;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;

    @Override
    public CompteDTO create(Long clientId, CompteCreateDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable pour id " + clientId));

        Compte compte = compteMapper.toEntity(dto);

        // valeurs par défaut
        if (compte.getDateOuverture() == null) {
            compte.setDateOuverture(LocalDate.now());
        }
        if (compte.getSolde() == null) {
            compte.setSolde(BigDecimal.ZERO);
        }

        compte.setClient(client);

        Compte saved = compteRepository.save(compte);
        return compteMapper.toDto(saved);
    }

    @Override
    public Optional<CompteDTO> getById(Long id) {
        return compteRepository.findById(id)
                .map(compteMapper::toDto);
    }

    @Override
    public List<CompteDTO> getByClient(Long clientId) {
        return compteRepository.findByClientId(clientId)
                .stream()
                .map(compteMapper::toDto)
                .toList();
    }

    @Override
    public Optional<CompteDTO> update(Long id, CompteUpdateDTO dto) {
        return compteRepository.findById(id)
                .map(existing -> {
                    compteMapper.updateEntity(existing, dto);
                    return compteMapper.toDto(existing);
                });
    }

    @Override
    public CompteDTO credit(Long compteId, BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec id " + compteId));

        compte.setSolde(compte.getSolde().add(montant));

        return compteMapper.toDto(compte);
    }

    @Override
    public CompteDTO debit(Long compteId, BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec id " + compteId));

        if (compte.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant");
        }

        compte.setSolde(compte.getSolde().subtract(montant));

        return compteMapper.toDto(compte);
    }

    @Override
    public void delete(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec id " + id));

        // Règle métier : on ne supprime que si solde = 0
        if (compte.getSolde().compareTo(BigDecimal.ZERO) != 0) {
            throw new RuntimeException(
                    "Impossible de supprimer le compte : le solde doit être à 0. Solde actuel = " + compte.getSolde()
            );
        }

        compteRepository.delete(compte);
    }
}
