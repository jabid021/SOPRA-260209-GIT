package duckflix.dao;

import java.util.List;

import duckflix.model.Film;
import duckflix.model.Media;
import duckflix.model.Serie;

public interface IDAOMedia extends IDAO<Media,Integer> {

	public List<Film> findAllFilm();
	public List<Serie> findAllSerie();
}
