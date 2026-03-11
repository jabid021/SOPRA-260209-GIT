package quest.context;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quest.dao.DAOFiliere;
import quest.dao.DAOMatiere;
import quest.dao.DAOModule;
import quest.dao.DAOOrdinateur;
import quest.dao.DAOPersonne;
import quest.dao.IDAOFiliere;
import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;

public class Singleton {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");

	private IDAOFiliere daoFiliere=new DAOFiliere();
	private IDAOMatiere daoMatiere = new DAOMatiere();
	private IDAOModule daoModule = new DAOModule();
	private IDAOOrdinateur daoOrdinateur = new DAOOrdinateur();
	private IDAOPersonne daoPersonne = new DAOPersonne();
	
	private static Singleton instance=null;
	
	
	private Singleton() {}
	
	public static Singleton getInstance() 
	{
		if(instance==null) {instance=new Singleton();}
		
		return instance;
	}

	public IDAOFiliere getDaoFiliere() {
		return daoFiliere;
	}

	public IDAOMatiere getDaoMatiere() {
		return daoMatiere;
	}

	public IDAOModule getDaoModule() {
		return daoModule;
	}

	public IDAOOrdinateur getDaoOrdinateur() {
		return daoOrdinateur;
	}

	public IDAOPersonne getDaoPersonne() {
		return daoPersonne;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}


	
	
	
	
	
}
