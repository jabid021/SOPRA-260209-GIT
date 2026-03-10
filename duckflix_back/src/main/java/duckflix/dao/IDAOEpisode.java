package duckflix.dao;

import java.util.List;

import duckflix.model.Episode;

public interface IDAOEpisode extends IDAO<Episode,Integer> {

	
	public List<Episode> findAllByDureeLowerThan(int duree);
}
