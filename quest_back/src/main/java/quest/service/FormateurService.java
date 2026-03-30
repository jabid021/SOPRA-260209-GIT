package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOPersonne;
import quest.model.Formateur;

@Service
public class FormateurService {

	@Autowired 
	IDAOPersonne daoPersonne;
	
	public Formateur getById(Integer id) 
	{
		return (Formateur) daoPersonne.findById(id).orElse(null);
	}
	
	public List<Formateur> getAll()
	{
		return daoPersonne.findAllFormateur();
	}
	
	public Formateur insert(Formateur formateur) 
	{
		return daoPersonne.save(formateur);
	}
	
	public Formateur update(Formateur formateur) 
	{
		return daoPersonne.save(formateur);
	}
	
	public void deleteById(Integer id) 
	{
		daoPersonne.deleteById(id);
	}
	
	public void delete(Formateur formateur) 
	{
		daoPersonne.delete(formateur);
	}
	
}
