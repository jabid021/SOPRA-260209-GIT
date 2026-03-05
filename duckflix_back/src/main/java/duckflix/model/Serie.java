package duckflix.model;

import java.util.List;


public class Serie extends Media {
	
	List<Saison> saisons;

	public Serie(Integer id, String titre, String description, List<Genre> genres, List<Utilisateur> utilisateurs,
			List<Saison> saisons) {
		super(id, titre, description, genres, utilisateurs);
		this.saisons = saisons;
	}

	public List<Saison> getSaisons() {
		return saisons;
	}

	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres + ", nbSaisons =" + saisons.size() +"]";
	}
	
	
}
