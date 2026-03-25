package eshop.test;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Produit;

public class TestSpring {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne; 
	
	
	public void run() {
		
			for(Client pe : daoPersonne.findAllClient()) 
			{
				System.out.println(pe);
			}
			
			
			for(Produit p : daoProduit.findAll()) 
			{
				System.out.println(p);
			}
			
			
			System.out.println("Voici les achats du client 1 : ");
			System.out.println(daoPersonne.findByIdWithAchats(1).getAchats());
			
			
			System.out.println("---------");
			System.out.println("Voici les achats du client 2 : ");
			System.out.println(daoPersonne.findByIdWithAchats(2).getAchats());
					
			
	}

}
