package com.simplecash.repository;

import com.simplecash.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // Tous les comptes d'un client
    List<Compte> findByClientId(Long clientId);
}
