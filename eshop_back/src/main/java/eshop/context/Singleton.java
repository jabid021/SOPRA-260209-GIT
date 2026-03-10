package eshop.context;

import eshop.dao.DAOAchat;
import eshop.dao.DAOPersonne;
import eshop.dao.DAOProduit;
import eshop.dao.IDAOAchat;
import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	private IDAOProduit daoProduit = new DAOProduit();
	private IDAOAchat daoAchat = new DAOAchat();
	private IDAOPersonne daoPersonne = new DAOPersonne();
	
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



	public IDAOProduit getDaoProduit() {
		return daoProduit;
	}



	public IDAOAchat getDaoAchat() {
		return daoAchat;
	}



	public IDAOPersonne getDaoPersonne() {
		return daoPersonne;
	}



	
	
	
}
