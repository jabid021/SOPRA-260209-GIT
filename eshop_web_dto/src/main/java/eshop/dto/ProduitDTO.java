package eshop.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import eshop.model.Achat;
import eshop.model.Produit;

public class ProduitDTO {

	private Integer id;
	private String libelle;
	private double prix;
	private Integer idFournisseur;
	private String nomFournisseur;
	private List<AchatDTO> ventesCustom;
	
	public static ProduitDTO convert(Produit produit) 
	{
		ProduitDTO prod = new ProduitDTO();
		BeanUtils.copyProperties(produit, prod);
		prod.idFournisseur=produit.getFournisseur().getId();
		prod.nomFournisseur=produit.getFournisseur().getNom();
		return prod;
	}
	
	
	public static ProduitDTO convertWithVentes(Produit produit) 
	{
		ProduitDTO prod = new ProduitDTO();
		BeanUtils.copyProperties(produit, prod);
		prod.idFournisseur=produit.getFournisseur().getId();
		prod.nomFournisseur=produit.getFournisseur().getNom();
		
		/*for(Achat a : produit.getVentes()) 
		{
			prod.ventesCustom.add(AchatDTO.convert(a));
		}*/
		prod.ventesCustom=produit.getVentes().stream().map(a->AchatDTO.convert(a)).toList();
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

	

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}
	
	

	public List<AchatDTO> getVentesCustom() {
		return ventesCustom;
	}


	public void setVentesCustom(List<AchatDTO> ventesCustom) {
		this.ventesCustom = ventesCustom;
	}


	@Override
	public String toString() {
		return "ProduitDTO [id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", idFournisseur=" + idFournisseur
				+ "]";
	}
	
	
}
