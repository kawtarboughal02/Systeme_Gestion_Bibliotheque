package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
