package quest.test;

import org.springframework.beans.factory.annotation.Autowired;

import quest.context.Singleton;
import quest.dao.DAOModule;
import quest.dao.DAOOrdinateur;
import quest.dao.DAOPersonne;

public class App {

	@Autowired
	DAOPersonne daoPersonne;
	
	@Autowired
	DAOModule daoModule;
	
	@Autowired
	DAOOrdinateur daoOrdinateur;
	
	public void run() {
	
		
		System.out.println(daoPersonne.findAllStagiaire());
		
		System.out.println(daoOrdinateur.findByMarque("Asuss"));
	
		System.out.println(daoModule.findByQuest(777));
	
		System.out.println("TOUT EST OK");
	}

}
