package duckflix.test;

import java.time.LocalDate;

import duckflix.model.Abonnement;
import duckflix.model.Episode;
import duckflix.model.Plan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		EntityManager em = emf.createEntityManager();
		
		
		Episode e1 = new Episode(1, 50, "L'eau est froide",null);
		Episode e2 = new Episode(2, 44, "Plumes disparues",null);
		Episode e3 = new Episode(3, 41, "Le reflet",null);
		Abonnement aboFree = new Abonnement("12", "Rue des Canards", "Lille", "59000");

		Abonnement aboPremium = new Abonnement(LocalDate.parse("2026-01-01"),LocalDate.parse("2026-12-31"),Plan.Premium, "8B", "Avenue Pixel", "Paris", "75011");

		
		
		em.getTransaction().begin();
		
		//Insert
		em.persist(e1);
		em.persist(e2);
		em.persist(e3);
		em.persist(aboFree);
		em.persist(aboPremium);
		
		
		em.getTransaction().commit();
		
		

		
		em.close();
		
		
		em = emf.createEntityManager();
		
		//findById
		Episode episodeFromBdd = em.find(Episode.class, 1)	;
		System.out.println(episodeFromBdd);
		
		
		//delete
		em.getTransaction().begin();
		em.remove(episodeFromBdd);
		em.getTransaction().commit();
		
		//findAll
		System.out.println(em.createQuery("from Episode").getResultList());
		
		//findAllByDuree JavaProgrammingQueryLanguage
		//System.out.println(em.createQuery("SELECT e from Episode e where e.duree<50").getResultList());
		em.close();
		
		
		
		
		
		
		emf.close();
	}

}
