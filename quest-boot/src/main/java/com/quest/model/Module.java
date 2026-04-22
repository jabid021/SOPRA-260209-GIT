package com.quest.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name="module")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique=true)
	@Digits(integer = 4,fraction = 0)
	private int quest;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate debut;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fin;

	@ManyToOne
	@JoinColumn(name="filiere",nullable = false)
	private Filiere filiere;

	@ManyToOne
	@JoinColumn(name="matiere",nullable = false)
	private Matiere matiere;


	public Module() {}

	public Module(Integer id, int quest, LocalDate debut, LocalDate fin, Filiere filiere, Matiere matiere) {
		this.id = id;
		this.quest = quest;
		this.debut = debut;
		this.fin = fin;
		this.filiere = filiere;
		this.matiere = matiere;
	}

	public Module(int quest, LocalDate debut, LocalDate fin, Filiere filiere, Matiere matiere) {
		this.quest = quest;
		this.debut = debut;
		this.fin = fin;
		this.filiere = filiere;
		this.matiere = matiere;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuest() {
		return quest;
	}

	public void setQuest(int quest) {
		this.quest = quest;
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

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", quest=" + quest + ", debut=" + debut + ", fin=" + fin + ", filiere=" + filiere
				+ ", matiere=" + matiere + "]";
	}


}
