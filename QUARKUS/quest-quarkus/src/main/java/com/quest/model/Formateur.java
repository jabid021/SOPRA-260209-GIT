package com.quest.model;

import jakarta.persistence.Entity;

@Entity
public class Formateur extends Personne {
	private boolean admin;

	public Formateur() {}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String getRole() {
		StringBuilder roleBuilder = new StringBuilder("formateur");

		if (this.admin) {
			roleBuilder.append(",admin");
		}

		return roleBuilder.toString();
	}
}
