package com.simplecash.repository;

import com.simplecash.entity.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
    List<Conseiller> findByAgenceId(String agenceId);
}
