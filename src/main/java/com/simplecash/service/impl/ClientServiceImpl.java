package com.simplecash.service.impl;

import com.simplecash.entity.Client;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.ConseillerRepository;
import com.simplecash.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ConseillerRepository conseillerRepository) {
        this.clientRepository = clientRepository;
        this.conseillerRepository = conseillerRepository;
    }

    @Override
    public Client create(Client client) {
        // Optionnel : vérifier conseiller, limite 10 clients...
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client existing = getById(id);
        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setAdresse(client.getAdresse());
        existing.setCodePostal(client.getCodePostal());
        existing.setVille(client.getVille());
        existing.setTelephone(client.getTelephone());
        existing.setTypeClient(client.getTypeClient());
        existing.setConseiller(client.getConseiller());
        return clientRepository.save(existing);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec id " + id));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getByConseiller(Long conseillerId) {
        return clientRepository.findByConseillerId(conseillerId);
    }
}
