package com.project;

import com.project.Entity.Adherent;
import com.project.Entity.Emprunt;
import com.project.Entity.Ouvrage;
import com.project.Entity.Reservation;
import com.project.Repository.AdherentRepository;
import com.project.Repository.EmpruntRepository;
import com.project.Repository.OuvrageRepository;
import com.project.Repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * Jeu de données de démonstration, activé uniquement sous le profil "demo".
 * Il alimente la base H2 au démarrage afin de peupler le catalogue, les
 * adhérents, les emprunts et les réservations pour la démonstration et la
 * génération de captures d'écran.
 *
 * L'insertion passe par la couche JPA (repositories) pour laisser Hibernate
 * gérer le mapping des colonnes, y compris les noms accentués.
 */
@Component
@Profile("demo")
public class DataSeeder implements ApplicationRunner {

    private final OuvrageRepository ouvrageRepository;
    private final AdherentRepository adherentRepository;
    private final EmpruntRepository empruntRepository;
    private final ReservationRepository reservationRepository;

    public DataSeeder(OuvrageRepository ouvrageRepository,
                      AdherentRepository adherentRepository,
                      EmpruntRepository empruntRepository,
                      ReservationRepository reservationRepository) {
        this.ouvrageRepository = ouvrageRepository;
        this.adherentRepository = adherentRepository;
        this.empruntRepository = empruntRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (ouvrageRepository.count() > 0) {
            return; // base déjà peuplée
        }

        // --- Ouvrages ---
        Ouvrage o1 = creerOuvrage("Victor Hugo", "Les Misérables", "Roman", "978-2070409228", 1985, 5);
        Ouvrage o2 = creerOuvrage("Antoine de Saint-Exupéry", "Le Petit Prince", "Conte", "978-2070612758", 1999, 8);
        Ouvrage o3 = creerOuvrage("George Orwell", "1984", "Science-fiction", "978-2070368228", 1972, 4);
        Ouvrage o4 = creerOuvrage("Gabriel García Márquez", "Cent ans de solitude", "Roman", "978-2020238113", 1995, 3);
        Ouvrage o5 = creerOuvrage("J. K. Rowling", "Harry Potter à l'école des sorciers", "Fantasy", "978-2070584628", 2017, 6);
        Ouvrage o6 = creerOuvrage("Albert Camus", "L'Étranger", "Roman", "978-2070360024", 1985, 7);

        // --- Adhérents (valide = true : validés ; false : demandes en attente) ---
        Adherent a1 = creerAdherent("Alaoui", "Yasmine", LocalDate.of(1998, 4, 12), "yasmine.alaoui@email.com", "12 Rue Ibn Sina, Casablanca", "0612345678", "Étudiant", true);
        Adherent a2 = creerAdherent("Benali", "Karim", LocalDate.of(1990, 9, 3), "karim.benali@email.com", "45 Avenue Hassan II, Rabat", "0623456789", "Enseignant", true);
        Adherent a3 = creerAdherent("El Fassi", "Salma", LocalDate.of(2001, 1, 25), "salma.elfassi@email.com", "8 Boulevard Zerktouni, Casablanca", "0634567890", "Étudiant", true);
        Adherent a4 = creerAdherent("Chakir", "Omar", LocalDate.of(1985, 6, 18), "omar.chakir@email.com", "23 Rue de la Liberté, Marrakech", "0645678901", "Retraité", true);

        // Demandes d'adhésion en attente de validation
        Adherent a5 = creerAdherent("Naji", "Lina", LocalDate.of(2003, 11, 7), "lina.naji@email.com", "77 Avenue Mohammed V, Fès", "0656789012", "Étudiant", false);
        Adherent a6 = creerAdherent("Tazi", "Mehdi", LocalDate.of(1995, 3, 30), "mehdi.tazi@email.com", "5 Rue Al Massira, Tanger", "0667890123", "Salarié", false);

        // --- Emprunts ---
        creerEmprunt("Alaoui", "Yasmine", LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 15), true, o1, a1);
        creerEmprunt("Benali", "Karim", LocalDate.of(2026, 9, 5), LocalDate.of(2026, 9, 19), false, o2, a2);
        creerEmprunt("El Fassi", "Salma", LocalDate.of(2026, 9, 10), LocalDate.of(2026, 9, 24), false, o3, a3);
        creerEmprunt("Chakir", "Omar", LocalDate.of(2026, 8, 20), LocalDate.of(2026, 9, 3), true, o4, a4);

        // --- Réservations ---
        Reservation r1 = new Reservation();
        r1.setOuvrage(o5);
        r1.setAdherent(a1);
        reservationRepository.save(r1);

        Reservation r2 = new Reservation();
        r2.setOuvrage(o6);
        r2.setAdherent(a2);
        reservationRepository.save(r2);
    }

    private Ouvrage creerOuvrage(String auteur, String titre, String categorie, String isbn, int annee, int qte) {
        Ouvrage o = new Ouvrage();
        o.setAuteur(auteur);
        o.setTitre(titre);
        o.setCategorie(categorie);
        o.setIsbn(isbn);
        o.setQuantité(qte);
        o.setDate_edition(new Date(annee - 1900, 0, 1));
        return ouvrageRepository.save(o);
    }

    private Adherent creerAdherent(String nom, String prenom, LocalDate naissance, String email,
                                   String adresse, String tel, String type, boolean valide) {
        Adherent a = new Adherent();
        a.setNom(nom);
        a.setPrenom(prenom);
        a.setDate_naissance(naissance);
        a.setEmail(email);
        a.setAdresse(adresse);
        a.setTelephone(Integer.parseInt(tel));
        a.setType_adherent(type);
        a.setValide(valide);
        return adherentRepository.save(a);
    }

    private Emprunt creerEmprunt(String nom, String prenom, LocalDate demande, LocalDate retour,
                                 boolean valide, Ouvrage ouvrage, Adherent adherent) {
        Emprunt e = new Emprunt();
        e.setNomEmprunteur(nom);
        e.setPrenomEmprunteur(prenom);
        e.setDateDemande(demande);
        e.setDateRetour(retour);
        e.setValide(valide);
        e.setOuvrage(ouvrage);
        e.setAdherent(adherent);
        return empruntRepository.save(e);
    }
}

