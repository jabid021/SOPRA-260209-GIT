package quest.dao;

import java.util.List;

import quest.model.Formateur;
import quest.model.Stagiaire;

public interface IDAOPersonne {
	
	public List<Stagiaire> findAllStagiaire();
	public List<Formateur> findAllFormateur();
	
}
