package duckflix.model;

import java.util.List;


public class Serie extends Media {
	
	private transient List<Saison> saisons;

	public Serie(Integer id, String titre, String description, List<Genre> genres) {
		super(id, titre, description, genres);
	}
	
	public Serie(String titre, String description, List<Genre> genres) {
		super(titre, description, genres);
	}

	public List<Saison> getSaisons() {
		return saisons;
	}

	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres + "s]";
	}
	
	/*@Override
	public String toString() {
		return "Serie [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres + ", nbSaisons =" + saisons.size() +"]";
	}
	*/
	
}
