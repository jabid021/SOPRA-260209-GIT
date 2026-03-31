package eshop.dto;

import org.springframework.beans.BeanUtils;

import eshop.model.Produit;

public class ProduitDTO {

	private Integer id;
	private String libelle;
	private double prix;
	private Integer idFournisseur;

	
	public static ProduitDTO convert(Produit produit) 
	{
		ProduitDTO prod = new ProduitDTO();
		BeanUtils.copyProperties(produit, prod);
		prod.idFournisseur=produit.getFournisseur().getId();
		return prod;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Integer getIdFournisseur() {
		return idFournisseur;
	}
	
	public void setIdFournisseur(Integer idFournisseur) {
		this.idFournisseur = idFournisseur;
	}


	@Override
	public String toString() {
		return "ProduitDTO [id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", idFournisseur=" + idFournisseur
				+ "]";
	}
	
	
}
