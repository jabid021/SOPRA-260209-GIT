package duckflix.model;

import java.util.List;

public abstract class Media {
	
	protected Integer id;
	protected String titre;
	protected String description;
	protected List<Genre> genres;
	protected List<Utilisateur> utilisateurs;
	
	public Media(Integer id, String titre, String description, List<Genre> genres, List<Utilisateur> utilisateurs) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.genres = genres;
		this.utilisateurs = utilisateurs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
