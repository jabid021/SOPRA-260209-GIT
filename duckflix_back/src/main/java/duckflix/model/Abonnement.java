package duckflix.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="subscription")
public class Abonnement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date_debut")
	private LocalDate dateDebut;
	@Column(name="date_fin")
	private LocalDate dateFin;
	
	@OneToOne(mappedBy = "abonnement") //Permet de pointer sur l'attribut java permettant la jointure sql entre un user et son abonnement
	private Utilisateur utilisateur;
	
	@Enumerated(EnumType.STRING)
	//Dans les precedentes versions de JPA il fallait preciser : 
	//@Column(columnDefinition = "ENUM('Free','Basic','Premium')")
	private Plan plan;
	
	@Embedded
	private Adresse adresse;
	
	
	public Abonnement() {}
	
	public Abonnement(Integer id, LocalDate dateDebut, LocalDate dateFin, Plan plan, String numero, String voie, String ville, String cp) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.plan = plan;
		this.adresse = new Adresse(numero, voie, ville, cp);
	}
	
	public Abonnement(LocalDate dateDebut, LocalDate dateFin, Plan plan, String numero, String voie, String ville, String cp) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.plan = plan;
		this.adresse = new Adresse(numero, voie, ville, cp);
	}

	public Abonnement(String numero, String voie, String ville, String cp) {
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

	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Abonnement [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", plan=" + plan
				+ ", adresse=" + adresse + "]";
	}
	
	
	
	
	
	
	
	

}
