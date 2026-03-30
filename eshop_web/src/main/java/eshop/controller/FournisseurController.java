package eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eshop.dao.IDAOPersonne;
import eshop.model.Fournisseur;
import eshop.model.Genre;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {

	@Autowired
	IDAOPersonne daoPersonne;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Fournisseur fournisseur = (Fournisseur) daoPersonne.findById(id).orElse(null);
		List<Fournisseur> fournisseurs = daoPersonne.findAllFournisseur();
		model.addAttribute("fournisseur", fournisseur);
		model.addAttribute("fournisseurs", fournisseurs);
		model.addAttribute("civilites",Genre.values());
		return "fournisseurs.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Fournisseur> fournisseurs = daoPersonne.findAllFournisseur();
		model.addAttribute("fournisseur", new Fournisseur());
		model.addAttribute("fournisseurs", fournisseurs);
		model.addAttribute("civilites",Genre.values());
		return "fournisseurs.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoPersonne.deleteById(id);
		return "redirect:/fournisseur";

	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Fournisseur fournisseur)  
	{	
		daoPersonne.save(fournisseur);
		return "redirect:/fournisseur";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@ModelAttribute Fournisseur fournisseur)  
	{
		daoPersonne.save(fournisseur);
		return "redirect:/fournisseur";
	}


}
