package com.simplecash.repository;

import com.simplecash.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    List<Compte> findByClientId(Long clientId);

    // Comptes de clients d'un type donné (PARTICULIER / ENTREPRISE) et solde < seuil
    List<Compte> findByClientTypeClientIgnoreCaseAndSoldeLessThan(String typeClient, BigDecimal soldeMax);
}
