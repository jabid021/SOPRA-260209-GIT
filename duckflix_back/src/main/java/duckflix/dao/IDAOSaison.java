package duckflix.dao;

import java.util.List;

import duckflix.model.Saison;

public interface IDAOSaison extends IDAO<Saison,Integer> {

	public List<Saison> findAllWithEpisodes();
	public Saison findByIdWithEpisodes(Integer id);
}
