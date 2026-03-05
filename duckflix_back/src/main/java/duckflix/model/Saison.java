package duckflix.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Saison 
{
	private int id;
	private LocalDate dateSortie;
	private List<Episode> episodes;
	
	public Saison(int id, LocalDate dateSortie) 
	{
		this.id = id;
		this.dateSortie = dateSortie;
		this.episodes = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "Saison [id=" + id + ", dateSortie=" + dateSortie + ", episodes=" + episodes + "]";
	}
	
}
