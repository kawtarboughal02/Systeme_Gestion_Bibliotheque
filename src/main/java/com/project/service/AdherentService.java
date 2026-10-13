package com.project.service;

import com.project.Entity.Adherent;
import com.project.Repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public void createAdherent(Adherent adherent) {
        adherentRepository.save(adherent);
    }

    public Adherent saveAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void supprimerAdherent(Long id) {
        adherentRepository.deleteById(id);
    }

    public Optional<Adherent> getAdherentById(Long id) {
        return adherentRepository.findById(id);
    }

    public Adherent findById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }


    public void validerAdherent(Long id) {
        Optional<Adherent> optionalAdherent = adherentRepository.findById(id);
        if (optionalAdherent.isPresent()) {
            Adherent adherent = optionalAdherent.get();
            adherent.setValide(Boolean.valueOf("Validé"));
            adherentRepository.save(adherent);
        }
    }
}
