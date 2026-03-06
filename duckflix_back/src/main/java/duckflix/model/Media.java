package duckflix.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="media")
public abstract class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(length = 50, nullable = false)
	protected String titre;
	@Column(columnDefinition = "TEXT")
	protected String description;
	
	@Enumerated(EnumType.STRING)
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
	
	public Media() {}

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
