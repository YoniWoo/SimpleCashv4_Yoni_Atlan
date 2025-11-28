package com.simplecash.repository;

import com.simplecash.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Tous les clients d'un conseiller
    List<Client> findByConseillerId(Long conseillerId);

    // Pour l'audit : tous les clients d'un type donné (PARTICULIER / ENTREPRISE)
    List<Client> findByTypeClientIgnoreCase(String typeClient);
}
