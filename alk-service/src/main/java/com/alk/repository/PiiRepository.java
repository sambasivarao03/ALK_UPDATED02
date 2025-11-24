package com.alk.repository;

import com.alk.model.Pii;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PiiRepository extends JpaRepository<Pii, String> {

    Optional<Pii> findByPiiPk(String piiPk);

    Optional<Pii> findByAlk(UUID alk);
}
