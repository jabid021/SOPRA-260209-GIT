package com.quest.model;

import jakarta.persistence.Entity;

@Entity
public class Stagiaire extends Personne {
	@Override
	public String getRole() {
		return "stagiaire";
	}
}
