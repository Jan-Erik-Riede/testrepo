package com.example.surfshop.repositories;

import com.example.surfshop.entities.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

    Optional<Benutzer> findByBenutzername(String benutzername);
}
