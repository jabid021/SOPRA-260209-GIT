package com.quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Stagiaire extends Personne {

	@Column(length = 30)
	private String email;

	@Embedded
	private Adresse adresse;

	@ManyToOne
	@JoinColumn(name="filiere")
	private Filiere filiere;

	@OneToOne(mappedBy = "utilisateur")
	private Ordinateur ordinateur;

	public Stagiaire() {}

	public Stagiaire(Integer id, String login, String password, String nom, String prenom, Genre civilite, String email,String numero,String voie,String ville,String cp,Filiere filiere) {
		super(id, login, password, nom, prenom,civilite);
		this.email = email;
		this.filiere = filiere;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}

	public Stagiaire(String login, String password, String nom, String prenom, Genre civilite, String email,String numero,String voie,String ville,String cp,Filiere filiere) {
		super(login, password, nom, prenom,civilite);
		this.email = email;
		this.filiere = filiere;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}



	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

	public String getInfosSelect()
	{
		return "Stagiaire "+id+" - "+prenom+" "+nom;
	}
	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", email=" + email + ", adresse=" + adresse + ", filiere="
				+ filiere + "]";
	}




}
