package com.project.controller;

import com.project.Entity.Adherent;
import com.project.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdherentController {
    @Autowired
    private AdherentService adherentService;

    @GetMapping("/listeadherent")
    public String adherent() {
        return "listeadherent";
    }

    @GetMapping("/listedemandeadherent")
    public String listeAdherents(Model model) {
        List<Adherent> adherents = adherentService.getAllAdherents();
        model.addAttribute("adherents", adherents);
        return "listedemandeadherent";
    }

    @GetMapping("/listeadherents")
    public String adherents(Model model) {
        List<Adherent> adherents = adherentService.getAllAdherents();
        model.addAttribute("adherents", adherents);
        return "listeadherents";
    }

    @GetMapping("/createAdherent")
    public String createAdherent() {
        return "createAdherent";
    }

    @PostMapping("/enregistrerAdherent")
    public String enregistrerAdherent(@ModelAttribute Adherent adherent) {
        adherentService.createAdherent(adherent);
        return "redirect:/listeadherents";
    }

    @GetMapping("/modifierAdherent/{id}")
    public String modifierAdherent(@PathVariable("id") Long id, Model model) {
        Optional<Adherent> adherent = adherentService.getAdherentById(id);
        model.addAttribute("adherent", adherent);
        return "modifierAdherent";
    }

    @GetMapping("/supprimerAdherent/{id}")
    public String supprimerAdherent(@PathVariable("id") Long id) {
        adherentService.supprimerAdherent(id);
        return "redirect:/listeadherents";
    }

    @GetMapping("/adherents/valider/{id}")
    public String validerAdherent(@PathVariable("id") Long id) {
        adherentService.validerAdherent(id);
        return "redirect:/listeadherents";
    }
}
