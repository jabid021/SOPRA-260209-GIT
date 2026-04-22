package com.quest.model;

import jakarta.persistence.Entity;

@Entity
public class Formateur extends Personne {
	private boolean admin;

	public Formateur() {}
	public Formateur(Integer id, String login, String password, String nom, String prenom, Genre civilite, boolean admin) {
		super(id, login, password, nom, prenom,civilite);
		this.admin = admin;
	}

	public Formateur(String login, String password, String nom, String prenom, Genre civilite, boolean admin) {
		super(login, password, nom, prenom,civilite);
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Formateur [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", admin=" + admin + "]";
	}


}
