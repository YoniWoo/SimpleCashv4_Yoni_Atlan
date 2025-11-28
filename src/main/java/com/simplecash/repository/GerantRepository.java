package com.simplecash.repository;

import com.simplecash.entity.Gerant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerantRepository extends JpaRepository<Gerant, Long> {
}
