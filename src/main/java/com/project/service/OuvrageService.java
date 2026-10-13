package com.project.service;


import com.project.Entity.Adherent;
import com.project.Entity.Ouvrage;
import com.project.Repository.OuvrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OuvrageService {

    @Autowired
    private OuvrageRepository OuvrageRepository;

    public Optional<Ouvrage> getOuvrageById(Long id) {
        return OuvrageRepository.findById(id);
    }

    public  void ajouterLivre(Ouvrage Ouvrage){
        OuvrageRepository.save(Ouvrage);
    }

    public List<Ouvrage> getAllOuvrages() {
        return OuvrageRepository.findAll();
    }

    public List<Ouvrage> rechercherOuvrages(String termeRecherche) {
        return OuvrageRepository.findByTitreContainingIgnoreCase(termeRecherche);
    }

    public Ouvrage findById(Long id) {
        return OuvrageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ouvrage non trouvé pour id : " + id));
    }


}
