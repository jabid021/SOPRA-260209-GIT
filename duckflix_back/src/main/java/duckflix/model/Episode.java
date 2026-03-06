package duckflix.model;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity//OBLIGATOIRE
@Table(name="episode")
public class Episode 	
{
	@Id//OBLIGATOIRE
	@GeneratedValue(strategy = GenerationType.IDENTITY) //SEMI-OBLIGATOIRE (permet l'auto increment classique de SQL)
	private Integer id;
	private int duree;
	private int numero;

	@Column(columnDefinition = "DECIMAL(11,2)") // DECIMAL(11,2) => 2 pour le nombre de decimales et 11 => le nombre de decimal + le nombre de chiffre avant la virgule
	private Double budget;
	private String titre;
	private transient Saison saison;


	public Episode() {}//CONSTRUCTEUR VIDE OBLIGATOIRE

	public Episode(Integer id, int numero,int duree, String titre,Double budget, Saison saison) 
	{
		this.id = id;
		this.duree = duree;
		this.numero = numero;
		this.titre = titre;
		this.budget=budget;
		this.saison = saison;
	}
	
	public Episode(int numero,int duree, String titre,Double budget, Saison saison) 
	{
		this.duree = duree;
		this.numero = numero;
		this.titre = titre;
		this.budget=budget;
		this.saison = saison;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", duree=" + duree + ", numero=" + numero + ", budget=" + budget + ", titre="
				+ titre + "]";
	}

	
}
