package duckflix.model;

public class Episode 
{
	private int id;
	private int duree;
	private int numero;
	private String titre;
	private Saison saison;

	public Episode(int id, int duree, int numero, String titre, Saison saison) 
	{
		super();
		this.id = id;
		this.duree = duree;
		this.numero = numero;
		this.titre = titre;
		this.saison = saison;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
