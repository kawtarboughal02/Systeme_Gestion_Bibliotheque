package com.project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Table(name = "adherent")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
	private LocalDate date_naissance;
    private String email;
    private String adresse;
	private Integer telephone;
    private String type_adherent;

    public Boolean getValide() {
        return valide;
    }

    private Boolean valide;

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Emprunt> emprunts;



}
