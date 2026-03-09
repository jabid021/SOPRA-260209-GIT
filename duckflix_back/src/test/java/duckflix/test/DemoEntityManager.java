package duckflix.test;

import duckflix.context.Singleton;
import duckflix.dao.IDAOCompte;
import duckflix.dao.IDAOEpisode;
import duckflix.model.Episode;

public class DemoEntityManager {

	public static void main(String[] args) {
	
		
		
		
		IDAOCompte daoC = Singleton.getInstance().getDaoCompte();
		IDAOEpisode daoE = Singleton.getInstance().getDaoEpisode();
		
		System.out.println(daoC.findAll());
		
		
		for(Episode e : daoE.findAllByDureeLowerThan(90)) 
		{
			System.out.println(e);
		}
		
	
		
		
		//persist(x) => x est managed
			//persist un objet x sans id => insert 
			//persist un objet x avec un id => refusé
		
		//Object copie = merge(x) => x n'est pas managed, copie lui est managed
			//merge un objet x sans id => insert
			//merge un objet x avec un id => update
		
		//find(Classe.class,id) => return un objet managed
		//em.createQuery(...) => return une liste d'objet managed
		//em.remove(Object x) => x doit etre managed 
		
		
		//dans les requetes "createQuery", on ecrit pas en SQL, mais en JPQL
		Singleton.getInstance().getEmf().close();
	
	}

}
