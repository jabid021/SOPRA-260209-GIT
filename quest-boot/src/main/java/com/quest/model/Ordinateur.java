package com.quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="ordinateur")
public class Ordinateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;

	@Column(nullable = false, columnDefinition = "VARCHAR(25) default 'Asus'")
	@Size(min = 4,max = 25,message = "La marque doit faire entre 4 et 25")
	@NotBlank(message="La marque ne peut pas etre vide")
	private String marque;

	@Column(nullable=false,columnDefinition = "int default 8")
	@PositiveOrZero
	private int ram;

	@OneToOne
	@JoinColumn(name="utilisateur")
	private Stagiaire utilisateur;

	public Ordinateur() {}

	public Ordinateur(Integer numero, String marque, int ram) {
		this.numero = numero;
		this.marque = marque;
		this.ram = ram;
	}

	public Ordinateur(String marque, int ram) {
		this.marque = marque;
		this.ram = ram;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}

	public Stagiaire getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Stagiaire utilisateur) {
		this.utilisateur = utilisateur;
	}
	@Override
	public String toString() {
		return "Ordinateur [numero=" + numero + ", marque=" + marque + ", ram=" + ram + ", utilisateur=" + utilisateur
				+ "]";
	}




}
