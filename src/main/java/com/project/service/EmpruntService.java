package com.project.service;

import com.project.Entity.Emprunt;
import com.project.Entity.Ouvrage;
import com.project.Repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;

    public void createEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }

    public void validerRetour(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid emprunt Id:" + empruntId));
        emprunt.setValide(true);
        empruntRepository.save(emprunt);
    }
    public Optional<Emprunt> findById(Long id) {
        return empruntRepository.findById(id);
    }



}
