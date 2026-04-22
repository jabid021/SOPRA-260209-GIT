package com.quest.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="filiere")
public class Filiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 35,nullable = false)
	@NotBlank
	@Size(min =10)
	private String libelle;

	@Column(nullable = false)
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate debut;

	@Column(nullable = false)
	//@DateTimeFormat(pattern="yyyy-MM-dd") pour les jsp
	@JsonFormat(pattern = "yyyy-MM-dd") //pour les api en json
	private LocalDate fin;


	@OneToMany(mappedBy="filiere")
	private List<Module> cours;

	@OneToMany(mappedBy="filiere")
	private List<Stagiaire> eleves;

	public Filiere() {}
	public Filiere(Integer id, String libelle, LocalDate debut, LocalDate fin) {
		this.id = id;
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
	}

	public Filiere(String libelle, LocalDate debut, LocalDate fin) {
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
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

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public List<Module> getCours() {
		return cours;
	}
	public void setCours(List<Module> cours) {
		this.cours = cours;
	}

	public String getInfoFiliere()
	{return "Filiere "+id+" - "+libelle;}

	public List<Stagiaire> getEleves() {
		return eleves;
	}
	public void setEleves(List<Stagiaire> eleves) {
		this.eleves = eleves;
	}
	@Override
	public String toString() {
		return "Filiere [id=" + id + ", libelle=" + libelle + ", debut=" + debut + ", fin=" + fin + "]";
	}


}
