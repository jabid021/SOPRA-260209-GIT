package duckflix.context;

import duckflix.dao.DAOAbonnement;
import duckflix.dao.DAOCompte;
import duckflix.dao.DAOEpisode;
import duckflix.dao.DAOMedia;
import duckflix.dao.DAOSaison;
import duckflix.dao.IDAOAbonnement;
import duckflix.dao.IDAOCompte;
import duckflix.dao.IDAOEpisode;
import duckflix.dao.IDAOMedia;
import duckflix.dao.IDAOSaison;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	
	private IDAOAbonnement daoAbonnement = new DAOAbonnement();
	private IDAOMedia daoMedia = new DAOMedia();
	private IDAOCompte daoCompte = new DAOCompte();
	private IDAOSaison daoSaison = new DAOSaison();
	private IDAOEpisode daoEpisode = new DAOEpisode();
	
	
	private static Singleton instance;
	
	
	
	private Singleton() {}



	public static Singleton getInstance() {
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}



	public EntityManagerFactory getEmf() {
		return emf;
	}



	public IDAOAbonnement getDaoAbonnement() {
		return daoAbonnement;
	}



	public IDAOMedia getDaoMedia() {
		return daoMedia;
	}



	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}



	public IDAOSaison getDaoSaison() {
		return daoSaison;
	}



	public IDAOEpisode getDaoEpisode() {
		return daoEpisode;
	}
	
	
	
	
}
