package com.project.Repository;

import com.project.Entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
