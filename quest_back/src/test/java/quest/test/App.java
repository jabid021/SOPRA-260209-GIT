package quest.test;

import org.springframework.beans.factory.annotation.Autowired;

import quest.dao.IDAOModule;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;

public class App {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOModule daoModule;
	
	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	public void run() {
		
		System.out.println(daoPersonne.findAllStagiaire());
		
		System.out.println(daoOrdinateur.findByMarque("Asuss"));
	
		System.out.println(daoModule.findByQuest(6865));
	
		System.out.println("TOUT EST OK");
	}

}
