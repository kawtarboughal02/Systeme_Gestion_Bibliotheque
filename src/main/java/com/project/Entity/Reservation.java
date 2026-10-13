package com.project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;


@Table(name = "reservation")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ouvrage_id")
    private Ouvrage ouvrage;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

}
