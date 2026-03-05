package duckflix.model;

import java.time.LocalDate;

public class Abonnement {
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Plan plan;
	private Adresse adresse;
	
	public Abonnement(Integer id, LocalDate dateDebut, LocalDate dateFin, Plan plan, String numero, String voie, String ville, String cp) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.plan = plan;
		this.adresse = new Adresse(numero, voie, ville, cp);
	}
	
	public Abonnement(Integer id, String numero, String voie, String ville, String cp) {
		this.id = id;
		this.dateDebut = null;
		this.dateFin = null;
		this.plan = Plan.Free;
		this.adresse = new Adresse(numero, voie, ville, cp);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(String numero, String voie, String ville, String cp) {
		this.adresse.setCp(cp);
		this.adresse.setNumero(numero);
		this.adresse.setVille(ville);
		this.adresse.setVoie(voie);
	}

	@Override
	public String toString() {
		return "Abonnement [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", plan=" + plan
				+ ", adresse=" + adresse + "]";
	}
	
	
	
	
	
	
	
	

}
