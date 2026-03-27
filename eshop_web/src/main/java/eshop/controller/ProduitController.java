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
import eshop.dao.IDAOProduit;
import eshop.model.Fournisseur;
import eshop.model.Produit;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Produit produit = daoProduit.findById(id).orElse(null);
		List<Produit> produits = daoProduit.findAll();
		List<Fournisseur> fournisseurs = daoPersonne.findAllFournisseur();
		model.addAttribute("produit", produit);
		model.addAttribute("produits", produits);
		model.addAttribute("fournisseurs",fournisseurs);
		return "produits.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Produit> produits = daoProduit.findAll();
		List<Fournisseur> fournisseurs = daoPersonne.findAllFournisseur();
		model.addAttribute("produit", new Produit());
		model.addAttribute("produits", produits);
		model.addAttribute("fournisseurs",fournisseurs);
		return "produits.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoProduit.deleteById(id);
		return "redirect:/produit";

	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Produit produit)  
	{	
		daoProduit.save(produit);
		return "redirect:/produit";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@ModelAttribute Produit produit)  
	{
		daoProduit.save(produit);
		return "redirect:/produit";
	}


}
