package duckflix.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="movie")
public class Film extends Media {
	
	private int duree;
	@Column(name="date_sortie")
	private LocalDate dateSortie;
	
	public Film() {}
	
	public Film(Integer id, String titre, String description, List<Genre> genres,
			int duree, LocalDate dateSortie) {
		super(id, titre, description, genres);
		this.duree = duree;
		this.dateSortie = dateSortie;
	}
	
	
	public Film(String titre, String description, List<Genre> genres,
			int duree, LocalDate dateSortie) {
		super(titre, description, genres);
		this.duree = duree;
		this.dateSortie = dateSortie;
	}
	
	
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", description=" + description + ", genres=" + genres
				+ ", duree=" + duree + ", dateSortie=" + dateSortie + "]";
	}

	
}
