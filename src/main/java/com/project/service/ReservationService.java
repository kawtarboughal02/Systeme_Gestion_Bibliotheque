package com.project.service;


import com.project.Entity.Emprunt;

import com.project.Entity.Ouvrage;
import com.project.Entity.Reservation;
import com.project.Repository.AdherentRepository;
import com.project.Repository.OuvrageRepository;
import com.project.Repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository ReservationRepository;
    @Autowired
    private OuvrageRepository ouvrageRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    public void createReservation(Reservation reservation) {
        ReservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return ReservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return ReservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + id));
    }

}
