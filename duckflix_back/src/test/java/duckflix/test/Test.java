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

public class Test {

	public static void main(String[] args) {
		// =========================
		// 1) ADMIN
		// =========================
		Admin admin = new Admin("admin", "admin123");

		// =========================
		// 2) ABONNEMENTS (Adresse embedded créée dans le constructeur)
		// Abonnement(plan, dateDebut, dateFin, numero, voie, ville, cp)
		// =========================
		Abonnement aboFree = new Abonnement("12", "Rue des Canards", "Lille", "59000");

		Abonnement aboPremium = new Abonnement(LocalDate.parse("2026-01-01"),LocalDate.parse("2026-12-31"),Plan.Premium, "8B", "Avenue Pixel", "Paris", "75011");

		// =========================
		// 3) UTILISATEURS
		// Utilisateur(login, password, email, abonnement)
		// (watchList gérée par add ensuite)
		// =========================
		Utilisateur u1 = new Utilisateur("leo", "leo123", "leo@duckflix.com", aboFree);
		Utilisateur u2 = new Utilisateur("maya", "maya123", "maya@duckflix.com", aboPremium);


		// =========================
		// 4) MEDIAS (10 films + 1 série)
		// Seule la liste de Genre est dans le constructeur.
		// =========================
		
		Film f1 = new Film("Duck Hard", "Un canard policier contre la mafia du pain.",
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
				Arrays.asList(Genre.Policier, Genre.Horreur),15999.99);

		// =========================
		// 5) SAISONS + EPISODES
		// Saison(dateSortie,serie) - épisodes ajoutés ensuite
		// Episode(saison, numero, titre, duree)
		// =========================
		Saison saison1 = new Saison(LocalDate.parse("2022-04-01"),s1);
		Saison saison2 = new Saison(LocalDate.parse("2023-04-01"),s1);


		Episode e1 = new Episode(1, 42, "L'eau est froide",saison1);
		Episode e2 = new Episode(2, 44, "Plumes disparues",saison1);
		Episode e3 = new Episode(3, 41, "Le reflet",saison1);

		Episode e4 = new Episode(1, 45, "Retour à l'étang",saison2);
		Episode e5 = new Episode(2, 43, "L'ombre du cygne",saison2);

		// =========================
		// 6) WATCHLISTS (add comme tu veux)
		// =========================
		u1.getWatchlist().add(f1);
		u1.getWatchlist().add(f4);
		u1.getWatchlist().add(f6);
		u1.getWatchlist().add(f10);
		u1.getWatchlist().add(s1);

		Collections.addAll(u2.getWatchlist(), f2,f3,f5,f7,f8,f9,s1);
		// =========================
		// 7) Mini affichage (optionnel)
		// =========================
		System.out.println("Admin: " + admin.getLogin());

		System.out.println("\nUser1: " + u1.getLogin() + " | " + u1.getAbonnement().getPlan());
		System.out.println("Watchlist u1 size = " + u1.getWatchlist().size());

		System.out.println("\nUser2: " + u2.getLogin() + " | " + u2.getAbonnement().getPlan());
		System.out.println("Watchlist u2 size = " + u2.getWatchlist().size());

		System.out.println("\nSerie: " + s1.getTitre() + " saisons=" + s1.getSaisons().size());
	}

}
