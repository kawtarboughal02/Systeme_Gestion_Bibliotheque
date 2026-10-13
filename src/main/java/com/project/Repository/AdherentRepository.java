package com.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Adherent;
import org.springframework.stereotype.Repository;


@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
}
