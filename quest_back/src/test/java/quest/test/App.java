package quest.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;
import quest.model.Matiere;

public class App {

	@Autowired
	IDAOPersonne daoPersonne;

	@Autowired
	IDAOModule daoModule;

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	@Autowired
	IDAOMatiere daoMatiere;
	public void run() {

		System.out.println(daoPersonne.findAllStagiaire());

		System.out.println(daoOrdinateur.findByMarque("Asus"));

		System.out.println(daoModule.findByQuest(6865));

		Matiere m =daoMatiere.findById(1).orElse(null);
		/*
		 Optional<Matiere> opt = daoMatiere.findById(1);
		 * if(opt.isEmpty()) 
		{
			System.out.println("Pas de matiere");
			m=null;
		}
		else 
		{
			m = opt.get();
		}*/
		System.out.println(m);

		System.out.println("TOUT EST OK");
	}

}
