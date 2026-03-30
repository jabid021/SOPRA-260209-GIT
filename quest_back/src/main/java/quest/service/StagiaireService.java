package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOPersonne;
import quest.model.Stagiaire;

@Service
public class StagiaireService {

	@Autowired 
	IDAOPersonne daoPersonne;
	
	public Stagiaire getById(Integer id) 
	{
		return (Stagiaire) daoPersonne.findById(id).orElse(null);
	}
	
	public List<Stagiaire> getAll()
	{
		return daoPersonne.findAllStagiaire();
	}
	
	public Stagiaire insert(Stagiaire stagiaire) 
	{
		return daoPersonne.save(stagiaire);
	}
	
	public Stagiaire update(Stagiaire stagiaire) 
	{
		if(stagiaire.getId()==null) 
		{
			throw new RuntimeException("Impossible de save un stagiaire sans id");
		}
		return daoPersonne.save(stagiaire);
	}
	
	public void deleteById(Integer id) 
	{
		daoPersonne.deleteById(id);
	}
	
	public void delete(Stagiaire stagiaire) 
	{
		daoPersonne.delete(stagiaire);
	}
	
}
