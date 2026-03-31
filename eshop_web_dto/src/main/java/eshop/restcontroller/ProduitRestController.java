package eshop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eshop.dao.IDAOProduit;
import eshop.dto.ProduitDTO;
import eshop.model.Produit;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {

	@Autowired
	IDAOProduit daoProduit;
	
	@GetMapping("/{id}")
	public ProduitDTO chercherById(@PathVariable Integer id) 
	{
		
		Produit produit = daoProduit.findById(id).orElse(null);
		System.out.println(ProduitDTO.convert(produit));
		return ProduitDTO.convert(produit);
	}
	
	@GetMapping("/{id}/ventes")
	public Produit chercherByIdWithVentes(@PathVariable Integer id) 
	{
		return daoProduit.findByIdWithVentes(id);
	}

	@GetMapping
	public List<Produit> chercherAll()  
	{
		return daoProduit.findAll();
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		daoProduit.deleteById(id);
	}
	
	@PostMapping
	public Produit ajouter(@RequestBody Produit produit)  
	{
		return daoProduit.save(produit);
	}
	
	@PutMapping("/{id}")
	public Produit modifier(@PathVariable Integer id,@RequestBody Produit produit)  
	{
		produit.setId(id);
		return daoProduit.save(produit);
	}

}
