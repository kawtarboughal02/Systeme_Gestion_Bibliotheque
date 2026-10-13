package com.project.controller;


import com.project.Entity.Adherent;
import com.project.Entity.Emprunt;


import com.project.Entity.Reservation;
import com.project.service.AdherentService;
import com.project.service.EmpruntService;
import com.project.service.OuvrageService;
import com.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.project.Entity.Ouvrage;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
public class OuvrageController {
	@Autowired
	private OuvrageService ouvrageService;
	@Autowired
	private EmpruntService empruntService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private AdherentService adherentService;

	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}

	@GetMapping("/ouvrages")
	public String Home(Model model){
		List<Ouvrage> ouvrages = ouvrageService.getAllOuvrages();
		model.addAttribute("ouvrages", ouvrages);
		return "ouvrages";
	}

	@GetMapping("/ajouterlivre")
	public String ajouterLivre()
	{
		return "ajouterLivre";
	}

	@PostMapping("/enregistrer")
	public String enregistrer(@ModelAttribute Ouvrage Ouvrage) {
		System.out.println("Livre enregistré : " + Ouvrage);
		ouvrageService.ajouterLivre(Ouvrage);
		return "redirect:/ouvrages";
	}

	@GetMapping("/modifier/{id}")
	public String modifierOuvrage(@PathVariable("id") Long id, Model model) {
		Optional<Ouvrage> ouvrage = ouvrageService.getOuvrageById(id);
		model.addAttribute("ouvrage", ouvrage);
		return "modifierOuvrage";
	}
	@GetMapping("/ouvrages/recherche")
	public String rechercherOuvrages(@RequestParam("q") String termeRecherche, Model model) {
		List<Ouvrage> ouvrages = ouvrageService.rechercherOuvrages(termeRecherche);
		model.addAttribute("ouvrages", ouvrages);
		return "ouvrages"; // Assurez-vous d'avoir une vue appropriée pour afficher les résultats de la recherche
	}


	@GetMapping("/ouvrages/emprunterForm")
	public String showEmpruntForm(@RequestParam("ouvrageId") Long ouvrageId, Model model) {
		Ouvrage ouvrage = ouvrageService.findById(ouvrageId);
		if (ouvrage == null) {
			return "redirect:/ouvrages";
		}

		Emprunt emprunt = new Emprunt();
		emprunt.setOuvrage(ouvrage);

		model.addAttribute("emprunt", emprunt);
		model.addAttribute("ouvrage", ouvrage);

		return "emprunt-form";
	}

	@PostMapping("/ouvrages/emprunter/{id}")
	public String processEmpruntForm(@PathVariable Long id,
									 @ModelAttribute("emprunt") Emprunt emprunt,
									 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "emprunt-form";
		}

		Ouvrage ouvrage = ouvrageService.findById(id);
		if (ouvrage == null) {
			bindingResult.rejectValue("ouvrage", "error.ouvrage", "Ouvrage non trouvé");
			return "emprunt-form";
		}

		emprunt.setOuvrage(ouvrage);
		empruntService.createEmprunt(emprunt);
		System.out.println("DONE");
		return "redirect:/ouvrages/emprunts";
	}


	@GetMapping("/ouvrages/emprunts")
	public String listEmprunts(Model model) {
		List<Emprunt> emprunts = empruntService.findAll();
		model.addAttribute("emprunts", emprunts);
		return "emprunts";
	}

	@GetMapping("/retour/{empruntId}/{action}")
	public String processRetourEmprunt(@PathVariable("empruntId") Long empruntId,
									   @PathVariable("action") String action) {
		if ("valider".equals(action)) {
			empruntService.validerRetour(empruntId);
		}
		return "redirect:/ouvrages/emprunts";
	}

	@GetMapping("/ouvrages/emprunts/modifier/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Emprunt emprunt = empruntService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid emprunt Id:" + id));
		model.addAttribute("emprunt", emprunt);
		return "modifierEmprunts";
	}

	@PostMapping("/ouvrages/emprunts/modifier/{id}")
	public String updateEmprunt(@PathVariable("id") Long id,
								@ModelAttribute("emprunt") Emprunt emprunt,
								BindingResult result, Model model) {
		if (result.hasErrors()) {
			emprunt.setId(id);
			return "modifierEmprunts";
		}
		empruntService.createEmprunt(emprunt);
		return "redirect:/ouvrages/emprunts";
	}

}


