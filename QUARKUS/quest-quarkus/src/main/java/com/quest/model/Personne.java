package com.quest.model;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="personne")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('Stagiaire','Formateur')")
@UserDefinition
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(length = 25,nullable = false,unique = true)
	@Username
	protected String login;

	@Column(length = 180,nullable = false)
	@Password
	protected String password;

	@Column(nullable = false,columnDefinition = "VARCHAR(30) default 'Doe'")
	protected String nom;

	@Column(length = 30,nullable = false)
	protected String prenom;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Homme','Femme','NB')",nullable = false)
	protected Civilite civilite;

	public Personne() {}

	public Personne(Integer id, String login, String password, String nom, String prenom,Civilite civilite) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite=civilite;
	}

	public Personne(String login, String password, String nom, String prenom,Civilite civilite) {
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite=civilite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	@Roles
	public abstract String getRole();
}
