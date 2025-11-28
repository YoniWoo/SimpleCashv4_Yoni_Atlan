package com.simplecash.service.impl;

import com.simplecash.entity.Client;
import com.simplecash.entity.Compte;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.CompteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public CompteServiceImpl(CompteRepository compteRepository,
                             ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Compte create(Compte compte, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable pour id " + clientId));

        compte.setClient(client);
        if (compte.getDateOuverture() == null) {
            compte.setDateOuverture(LocalDate.now());
        }
        if (compte.getSolde() == null) {
            compte.setSolde(BigDecimal.ZERO);
        }
        return compteRepository.save(compte);
    }

    @Override
    public Compte getById(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec id " + id));
    }

    @Override
    public List<Compte> getByClient(Long clientId) {
        return compteRepository.findByClientId(clientId);
    }

    @Override
    public Compte credit(Long compteId, BigDecimal montant) {
        if (montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        Compte compte = getById(compteId);
        compte.setSolde(compte.getSolde().add(montant));
        return compteRepository.save(compte);
    }

    @Override
    public Compte debit(Long compteId, BigDecimal montant) {
        if (montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        Compte compte = getById(compteId);
        // Version simple : pas de découvert
        if (compte.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant");
        }
        compte.setSolde(compte.getSolde().subtract(montant));
        return compteRepository.save(compte);
    }

    @Override
    public void delete(Long compteId) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec id " + compteId));

        // Règle métier : on ne supprime que si le solde est à 0
        if (compte.getSolde().compareTo(BigDecimal.ZERO) != 0) {
            throw new RuntimeException(
                    "Impossible de supprimer le compte : le solde doit être à 0. Solde actuel = "
                            + compte.getSolde()
            );
        }

        compteRepository.delete(compte);
    }

}
