package quest.context;

import quest.dao.DAOFiliere;
import quest.dao.DAOMatiere;
import quest.dao.DAOModule;
import quest.dao.DAOOrdinateur;
import quest.dao.DAOPersonne;

public class Singleton {

	private DAOFiliere daoFiliere=new DAOFiliere();
	private DAOMatiere daoMatiere = new DAOMatiere();
	private DAOModule daoModule = new DAOModule();
	private DAOOrdinateur daoOrdinateur = new DAOOrdinateur();
	private DAOPersonne daoPersonne = new DAOPersonne();
	
	private static Singleton instance=null;
	
	
	private Singleton() {}
	
	public static Singleton getInstance() 
	{
		if(instance==null) {instance=new Singleton();}
		
		return instance;
	}

	public DAOFiliere getDaoFiliere() {
		return daoFiliere;
	}

	public DAOMatiere getDaoMatiere() {
		return daoMatiere;
	}

	public DAOModule getDaoModule() {
		return daoModule;
	}

	public DAOOrdinateur getDaoOrdinateur() {
		return daoOrdinateur;
	}

	public DAOPersonne getDaoPersonne() {
		return daoPersonne;
	}
	
	
	
	
	
}
