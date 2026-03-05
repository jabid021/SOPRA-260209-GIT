package eshop.test;

import eshop.model.Personne;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		Personne personne1 = new Personne("Abid","Jordan");
		Personne personne2 = new Personne("Perrouault","Jeremy");
		Personne personne3 = new Personne("Abid","Charly");
		

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
