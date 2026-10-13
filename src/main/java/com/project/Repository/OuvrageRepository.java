package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Ouvrage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuvrageRepository extends JpaRepository<Ouvrage, Long> {

    List<Ouvrage> findByTitreContainingIgnoreCase(String termeRecherche);
}
