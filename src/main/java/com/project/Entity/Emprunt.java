package com.project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "emprunt")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomEmprunteur;
    private String prenomEmprunteur;
    private LocalDate dateDemande;
    private LocalDate dateRetour;
    private Boolean valide;

    @ManyToOne
    @JoinColumn(name = "ouvrage_id")
    private Ouvrage ouvrage;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;


}