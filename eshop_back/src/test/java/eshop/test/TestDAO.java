package eshop.test;

import eshop.context.Singleton;
import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Produit;

public class TestDAO {

	public static void main(String[] args) {
			IDAOProduit daoProduit =Singleton.getInstance().getDaoProduit();
			IDAOPersonne daoPersonne = Singleton.getInstance().getDaoPersonne();
			
			
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
			
			Singleton.getInstance().getEmf().close();
			

	}

}
