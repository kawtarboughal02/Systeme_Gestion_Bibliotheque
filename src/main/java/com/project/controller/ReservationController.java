package com.project.controller;

import com.project.Entity.Adherent;
import com.project.Entity.Emprunt;
import com.project.Entity.Ouvrage;
import com.project.Entity.Reservation;
import com.project.service.AdherentService;
import com.project.service.EmpruntService;
import com.project.service.OuvrageService;
import com.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private OuvrageService ouvrageService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private AdherentService adherentService;

    @GetMapping("/reservation")
    public String Reservation()
    {
        return "reservation";
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

}
