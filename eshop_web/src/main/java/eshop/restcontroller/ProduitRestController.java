package eshop.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import eshop.model.Produit;

public class ProduitRestController {

	

	public Produit chercherById() 
	{
		return null;
	}
	
	@GetMapping("/api/produit/{id}/ventes")
	public Produit chercherByIdWithVentes() 
	{
		return null;
	}

	public List<Produit> chercherAll()  
	{
		return null;
	}

	public void supprimer()  
	{
		
	}
	
	public Produit ajouter()  
	{
		return null;
	}

	public Produit modifier()  
	{
		return null;
	}


}
