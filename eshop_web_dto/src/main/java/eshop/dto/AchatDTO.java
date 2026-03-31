package eshop.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import eshop.model.Achat;

public class AchatDTO {

	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateAchat;
	private int quantite;
	private Integer idClient;
	private Integer idProduit;
	
	public static AchatDTO convert(Achat achat) 
	{
		AchatDTO ach = new AchatDTO();
		BeanUtils.copyProperties(achat, ach);
		ach.idClient=achat.getClient().getId();
		ach.idProduit=achat.getProduit().getId();
		return ach;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}
	
	
	
}
