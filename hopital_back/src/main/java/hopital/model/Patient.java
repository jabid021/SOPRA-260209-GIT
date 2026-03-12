package hopital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="patient")
public class Patient {
 
	@Id
	private Integer id;
	@Column(length = 50,nullable = false)
	private String prenom;
	@Column(length = 50,nullable = false)
	private String nom;
	
	public Patient() {}
	public Patient(Integer id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", prenom=" + prenom + ", nom=" + nom + "]";
	}
	
	
	
}
