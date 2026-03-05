package duckflix.model;

import java.util.List;

public abstract class Media {
	
	protected Integer id;
	protected String titre;
	protected String description;
	protected List<Genre> genres;
	protected transient List<Utilisateur> utilisateurs;
	
	public Media(Integer id, String titre, String description,List<Genre> genres) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.genres=genres;
	}
	
	public Media(String titre, String description,List<Genre> genres) {
		this.titre = titre;
		this.description = description;
		this.genres=genres;
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
