package duckflix.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import duckflix.model.Abonnement;
import duckflix.model.Admin;
import duckflix.model.Episode;
import duckflix.model.Film;
import duckflix.model.Genre;
import duckflix.model.Plan;
import duckflix.model.Saison;
import duckflix.model.Serie;
import duckflix.model.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		EntityManager em = emf.createEntityManager();
		
		
		/*Film f1 = new Film("Duck Hard", "Un canard policier contre la mafia du pain.",
				Arrays.asList(Genre.Policier, Genre.Comedie), 128, LocalDate.parse("2021-05-12"));

		Film f2 = new Film("Quackception", "Des rêves imbriqués mais version canard.",
				Arrays.asList(Genre.Documentaire), 142, LocalDate.parse("2020-11-06"));

		Film f3 = new Film("Feather & Furious", "Courses illégales sur scooters aquatiques.",
				Arrays.asList(Genre.Comedie), 110, LocalDate.parse("2019-07-02"));

		Film f4 = new Film("Horror Pond", "Ils n'auraient jamais dû camper au bord de l'étang.",
				Arrays.asList(Genre.Horreur), 97, LocalDate.parse("2022-10-31"));

		Film f5 = new Film("Anime Duck Saga", "Un héros à plumes découvre son destin.", Arrays.asList(Genre.Anime), 104,
				LocalDate.parse("2018-03-18"));

		Film f6 = new Film("Le Mystère du Bec", "Une enquête à l'ancienne dans une ville brumeuse.",
				Arrays.asList(Genre.Policier), 121, LocalDate.parse("2023-02-03"));

		Film f7 = new Film("Docu: Migration", "Le grand voyage des canards, version premium.",
				Arrays.asList(Genre.Documentaire), 88, LocalDate.parse("2017-09-25"));

		Film f8 = new Film("Comédie de l'Étang", "Des voisins impossibles à supporter sur les berges.",
				Arrays.asList(Genre.Comedie), 99, LocalDate.parse("2016-06-01"));

		Film f9 = new Film("Night Quacks", "Quand la nuit tombe, les plumes se lèvent.",
				Arrays.asList(Genre.Horreur, Genre.Policier), 107, LocalDate.parse("2024-01-20"));

		Film f10 = new Film("Le Canard et le Monde", "Une aventure douce et absurde.",
				Arrays.asList(Genre.Comedie, Genre.Documentaire), 115, LocalDate.parse("2015-12-15"));

		Serie s1 = new Serie("DuckFlix Originals : The Pond", "Une série dramatique au bord d'un étang pas si calme...",
				Arrays.asList(Genre.Policier, Genre.Horreur));

		
		
		Saison saison1 = new Saison(LocalDate.parse("2022-04-01"));
		Saison saison2 = new Saison(LocalDate.parse("2023-04-01"));

		
		Episode e1 = new Episode(1, 42, "L'eau est froide",2500.00,s1,saison1);
		Episode e2 = new Episode(2, 44, "Plumes disparues",1580.50,s1,saison1);
		Episode e3 = new Episode(3, 41, "Le reflet",250.99,s1,saison1);

		Episode e4 = new Episode(1, 45, "Retour à l'étang",1522.99,s1,saison2);
		Episode e5 = new Episode(2, 43, "L'ombre du cygne",35000.855574,s1,saison2);
		
		Abonnement aboFree = new Abonnement("12", "Rue des Canards", "Lille", "59000");

		Abonnement aboPremium = new Abonnement(LocalDate.parse("2026-01-01"),LocalDate.parse("2026-12-31"),Plan.Premium, "8B", "Avenue Pixel", "Paris", "75011");

		Admin admin = new Admin("admin", "admin123");
		Utilisateur u1 = new Utilisateur("leo", "leo123", "leo@duckflix.com", aboFree);
		Utilisateur u2 = new Utilisateur("maya", "maya123", "maya@duckflix.com", aboPremium);

		u1.getWatchlist().add(f1);
		//u1.getWatchlist().add(f1); 
		u1.getWatchlist().add(f4);
		u1.getWatchlist().add(f6);
		u1.getWatchlist().add(f10);
		u1.getWatchlist().add(s1);
		 

		Collections.addAll(u2.getWatchlist(), f2,f3,f5,f7,f8,f9,s1);
		
		em.getTransaction().begin();
		
		//Insert
		em.persist(f1);
		em.persist(f2);
		em.persist(f3);
		em.persist(f4);
		em.persist(f5);
		em.persist(f6);
		em.persist(f7);
		em.persist(f8);
		em.persist(f9);
		em.persist(f10);
		em.persist(s1);
		em.persist(saison1);
		em.persist(saison2);
		em.persist(e1);
		em.persist(e2);
		em.persist(e3);
		em.persist(e4);
		em.persist(e5);
		em.persist(aboFree);
		em.persist(aboPremium);
		em.persist(admin);
		em.persist(u1);
		em.persist(u2);
		
		em.getTransaction().commit();
		
		

		
		em.close();
		
		
		em = emf.createEntityManager();
		
		//findById
		Episode episodeFromBdd = em.find(Episode.class, 1)	;
		System.out.println(episodeFromBdd);
		
		
		//delete
		em.getTransaction().begin();
		//em.remove(episodeFromBdd);
		em.getTransaction().commit();
		
		//findAll
		System.out.println(em.createQuery("from Episode").getResultList());
		System.out.println(em.createQuery("from Serie").getResultList());
		//findAllByDuree JavaProgrammingQueryLanguage
		//System.out.println(em.createQuery("SELECT e from Episode e where e.duree<50").getResultList());
		em.close();
		
		
		*/
		
		
		
		emf.close();
	}

}
