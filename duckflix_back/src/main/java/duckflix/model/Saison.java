package duckflix.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Saison 
{
	private Integer id;
	private LocalDate dateSortie;
	private transient Serie serie;
	private transient List<Episode> episodes=new ArrayList();
	
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
