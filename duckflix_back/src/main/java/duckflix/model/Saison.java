package duckflix.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="saison")
public class Saison 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date_sortie")
	private LocalDate dateSortie;
	
	
	@ManyToOne
	private Serie serie;
	
	private transient List<Episode> episodes=new ArrayList();
	
	
	
	public Saison() {}
	
	public Saison(Integer id,LocalDate dateSortie, Serie serie) 
	{
		this.id = id;
		this.dateSortie = dateSortie;
		this.serie=serie;
	}
	
	public Saison(LocalDate dateSortie, Serie serie) 
	{
		this.dateSortie = dateSortie;
		this.serie=serie;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
	
	

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "Saison [id=" + id + ", dateSortie=" + dateSortie + ", serie=" + serie + "]";
	}

	
}
