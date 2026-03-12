package quest.dao;

import java.util.List;

import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IDAOPersonne extends IDAO<Personne,Integer> {
	
	public List<Stagiaire> findAllStagiaire();
	public List<Formateur> findAllFormateur();
	
}
