package hopital.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Visite")
public class Visite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	private int salle;
	private double prix;
	@Column(name="date_visite",nullable=false)
	private LocalDate dateVisite;

	@ManyToOne
	@JoinColumn(name="id_patient")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="id_medecin",nullable = false)
	private Medecin medecin;
	
	
	public Visite() {}
	public Visite(Integer numero, Patient patient, Medecin medecin, int salle, double prix, LocalDate dateVisite) {
		this.numero = numero;
		this.patient = patient;
		this.medecin = medecin;
		this.salle = salle;
		this.prix = prix;
		this.dateVisite = dateVisite;
	}

	public Visite(Patient patient, Medecin medecin) {
		this.patient = patient;
		this.medecin = medecin;
		this.salle = medecin.getSalle();
		this.prix = 20;
		this.dateVisite = LocalDate.now();
	}


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}


	public int getSalle() {
		return salle;
	}


	public void setSalle(int salle) {
		this.salle = salle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public LocalDate getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}


	@Override
	public String toString() {
		return "Visite [numero=" + numero + ", patient=" + patient + ", medecin=" + medecin.getId() + ", salle=" + salle
				+ ", prix=" + prix + ", dateVisite=" + dateVisite + "]";
	}




}
