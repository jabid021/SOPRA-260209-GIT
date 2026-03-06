package eshop.test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		Client personne1 = new Client("Abid","Jordan",Genre.homme, LocalDate.parse("1993-05-01"),"161","Avenu de Verdun","Ivry sur Seine","92400");
		Client personne2 = new Client("Perrouault","Jeremy",null, LocalDate.parse("1983-07-15"),"161","Avenu de Verdun","Ivry sur Seine","92400");
		Fournisseur personne3 = new Fournisseur("Abid","Charly",Genre.homme,"AJC Ingenierie");
		

		Produit produit1 = new Produit("Formation SQL",980,personne3);
		Produit produit2 = new Produit("Formation Spring",1450.50,personne3);
		
		
		personne1.getAchats().add(produit1);

		Collections.addAll(personne2.getAchats(), produit1,produit2);
		
		em.getTransaction().begin();
		
			em.persist(personne1);
			em.persist(personne2);
			em.persist(personne3);
			em.persist(produit1);
			em.persist(produit2);
			
		em.getTransaction().commit();
		
		
		
		em.close();
		emf.close();
	}

}
