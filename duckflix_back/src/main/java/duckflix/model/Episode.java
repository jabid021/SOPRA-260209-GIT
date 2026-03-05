package duckflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity//OBLIGATOIRE
public class Episode 
{
	@Id//OBLIGATOIRE
	@GeneratedValue(strategy = GenerationType.IDENTITY) //SEMI-OBLIGATOIRE (permet l'auto increment classique de SQL)
	private Integer id;
	private int duree;
	private int numero;
	private String titre;
	private transient Saison saison;


	public Episode() {}//CONSTRUCTEUR VIDE OBLIGATOIRE

	public Episode(Integer id, int numero,int duree, String titre, Saison saison) 
	{
		this.id = id;
		this.duree = duree;
		this.numero = numero;
		this.titre = titre;
		this.saison = saison;
	}
	
	public Episode(int numero,int duree, String titre, Saison saison) 
	{
		this.duree = duree;
		this.numero = numero;
		this.titre = titre;
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

	@Override
	public String toString() {
		return "Episode [id=" + id + ", duree=" + duree + ", numero=" + numero + ", titre=" + titre + ", saison="
				+ saison + "]";
	}
	
	
}
