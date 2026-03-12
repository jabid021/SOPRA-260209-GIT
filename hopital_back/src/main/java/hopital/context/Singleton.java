package hopital.context;

import hopital.dao.DAOCompte;
import hopital.dao.DAOPatient;
import hopital.dao.DAOVisite;
import hopital.dao.IDAOCompte;
import hopital.dao.IDAOPatient;
import hopital.dao.IDAOVisite;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	private IDAOCompte daoCompte=new DAOCompte();
	private IDAOPatient daoPatient = new DAOPatient();
	private IDAOVisite daoVisite = new DAOVisite();
	
	private static Singleton instance=null;
	
	
	private Singleton() {}
	
	public static Singleton getInstance() 
	{
		if(instance==null) {instance=new Singleton();}
		
		return instance;
	}
	
	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}
	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}
	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	
}
