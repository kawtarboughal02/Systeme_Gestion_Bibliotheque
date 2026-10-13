package com.project.Entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "ouvrage")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Ouvrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_edition;
    private String titre;
    private String isbn;
    private String auteur;
    private String categorie;
    private Integer quantité;

    @OneToMany(mappedBy = "ouvrage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Emprunt> emprunts;

    @OneToMany(mappedBy = "ouvrage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;


}
