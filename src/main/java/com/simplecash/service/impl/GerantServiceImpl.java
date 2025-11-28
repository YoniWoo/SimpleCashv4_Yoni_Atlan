package com.simplecash.service.impl;

import com.simplecash.entity.Agence;
import com.simplecash.entity.Conseiller;
import com.simplecash.entity.Gerant;
import com.simplecash.repository.ConseillerRepository;
import com.simplecash.repository.GerantRepository;
import com.simplecash.service.GerantService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GerantServiceImpl implements GerantService {

    private final GerantRepository gerantRepository;
    private final ConseillerRepository conseillerRepository;

    public GerantServiceImpl(GerantRepository gerantRepository,
                             ConseillerRepository conseillerRepository) {
        this.gerantRepository = gerantRepository;
        this.conseillerRepository = conseillerRepository;
    }

    @Override
    public Gerant getById(Long id) {
        return gerantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gérant introuvable avec id " + id));
    }

    @Override
    public Conseiller addConseiller(Long gerantId, Conseiller conseiller) {
        // 1. Récupérer le gérant
        Gerant gerant = gerantRepository.findById(gerantId)
                .orElseThrow(() -> new RuntimeException("Gérant introuvable avec id " + gerantId));

        // 2. Récupérer l'agence du gérant
        Agence agence = gerant.getAgence();
        if (agence == null) {
            throw new RuntimeException("Le gérant avec id " + gerantId + " n'est lié à aucune agence");
        }

        // 3. Associer l'agence au conseiller
        conseiller.setAgence(agence);

        // 4. Sauvegarder le conseiller
        return conseillerRepository.save(conseiller);
    }
}
