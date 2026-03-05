package duckflix.model;

import java.util.List;


public class Serie extends Media {
	
	private Double budget;
	private transient List<Saison> saisons;

	public Serie(Integer id, String titre, String description, List<Genre> genres) {
		super(id, titre, description, genres);
	}
	
	public Serie(String titre, String description, List<Genre> genres,Double budget) {
		super(titre, description, genres);
		this.budget=budget;
	}

	public List<Saison> getSaisons() {
		return saisons;
	}

	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}
	
	

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres
				+ ", utilisateurs=" + utilisateurs + ", budget=" + budget + "]";
	}


	/*@Override
	public String toString() {
		return "Serie [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres + ", nbSaisons =" + saisons.size() +"]";
	}
	*/
	
}
