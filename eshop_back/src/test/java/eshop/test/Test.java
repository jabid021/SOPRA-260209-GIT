package eshop.test;

import java.time.LocalDate;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Personne;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		Personne personne1 = new Client("Abid","Jordan",Genre.homme, LocalDate.parse("1993-05-01"),"161","Avenu de Verdun","Ivry sur Seine","92400");
		Personne personne2 = new Client("Perrouault","Jeremy",null, LocalDate.parse("1983-07-15"),"161","Avenu de Verdun","Ivry sur Seine","92400");
		Personne personne3 = new Fournisseur("Abid","Charly",Genre.homme,"AJC Ingenierie");
		

		Produit produit1 = new Produit("Formation SQL",980);
		Produit produit2 = new Produit("Formation Spring",1450.50);
		
		
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
