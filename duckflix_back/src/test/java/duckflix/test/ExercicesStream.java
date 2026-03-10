package duckflix.test;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.dao.IDAOCompte;
import duckflix.dao.IDAOMedia;
import duckflix.model.Film;
import duckflix.model.Utilisateur;

public class ExercicesStream {

	public static void main(String[] args) {
		IDAOMedia daoFilm = Singleton.getInstance().getDaoMedia();
		IDAOCompte daoCompte = Singleton.getInstance().getDaoCompte();

		List<Film> films = daoFilm.findAllFilm();
		List<Utilisateur> users = daoCompte.findAllUtilisateurWithWatchList();


		//Récupérer u1/u2 si présents
		Utilisateur u1 = users.stream()
				.filter(u -> u.getLogin().equals("leo"))
				.findFirst()
				.orElse(null);

		Utilisateur u2 = users.stream()
				.filter(u -> u.getLogin().equals("maya"))
				.findFirst()
				.orElse(null);


		System.out.println(u1+" - "+u1.getWatchlist());

		System.out.println(u2+" - "+u2.getWatchlist());
		//Rappel d'utilisation de stream / filter / toList

		List<Film> filmId1ou2 = films.stream().filter(f->f.getId()==1 || f.getId()==2).toList();
		
		


		// =========================================================
		// EXERCICE 1 — Filtrer 
		// Objectif : créer une liste filmsLongs contenant les films de durée >= 120.
		// Indice : utiliser stream() + filter() + toList()
		// =========================================================
		// List<Film> filmsLongs = ...








		// =========================================================
		// EXERCICE 2 — Filtrer sur String 
		// Objectif : créer une liste filmsAvecDuck contenant les films dont le titre contient "Duck" (ignore case).
		// Indice : filter() + toLowerCase() + contains()
		// =========================================================
		// List<Film> filmsAvecDuck = ...






		// =========================================================
		// EXERCICE 3 — map 
		// Objectif : créer une liste titresFilms contenant uniquement les titres de tous les films.
		// Indice : map(f -> f.getTitre())
		// =========================================================
		// List<String> titresFilms = ...






		// =========================================================
		// EXERCICE 4 — tri AZ 
		// Objectif : créer une liste filmsTriesParTitre triée par titre A -> Z.
		// Indice : sorted((a,b) -> a.getTitre().compareTo(b.getTitre()))
		// =========================================================
		// List<Film> filmsTriesParTitre = ...







		// =========================================================
		// EXERCICE 5 — tri décroissant 
		// Objectif : créer une liste filmsTriesParDureeDesc triée par durée décroissante.
		// Indice : sorted((a,b) -> Integer.compare(a.getDuree(), b.getDuree())).toList().reversed()
		// =========================================================
		// List<Film> filmsTriesParDureeDesc = ...








		// =========================================================
		// EXERCICE 6 — max
		// Objectif : trouver filmLePlusLong (film de durée maximale), ou null si la liste est vide.
		// Indice : max((a,b)->...) + orElse(null)
		// =========================================================
		// Film filmLePlusLong = ...







		// =========================================================
		// EXERCICE 7 — mapToInt + max 
		// Objectif : calculer dureeMaxWatchlistU1 : durée max parmi les FILMS de la watchlist de u1.
		// Si u1 est null => 0. Si aucun film dans watchlist => 0.
		// =========================================================
		// int dureeMaxWatchlistU1 = ...





		// =========================================================
		// EXERCICE 8 — count 
		// Objectif : compter nbFilmsCriteres : films dont
		//  - titre longueur >= 10
		//  - ET (Genre.Horreur OU Genre.Policier)
		// Indice : filter() + count() + getGenres().contains(...)
		// =========================================================
		// long nbFilmsCriteres = ...







		// =========================================================
		// EXERCICE 9 — allMatch / anyMatch / noneMatch 
		// À partir de users :
		// 1) tousOntUneWatchlistNonVide : tous les utilisateurs ont une watchlist non vide

		// 2) auMoinsUnPremium : au moins un utilisateur a un abonnement Premium

		// 3) aucunEmailGmail : aucun utilisateur n'a un email finissant par "@gmail.com"
		//    Indice :.endsWith("@gmail.com")
		// =========================================================
		// boolean tousOntUneWatchlistNonVide = ...
		// boolean auMoinsUnPremium = ...
		// boolean aucunEmailGmail = ...


		// =========================================================
		// Affichage rapide 
		// =========================================================

		//System.out.println("Ex1 filmsLongs = " + filmsLongs.size());
		//System.out.println("Ex2 filmsAvecDuck = " + filmsAvecDuck.size());
		//System.out.println("Ex3 titresFilms = " + titresFilms);
		//System.out.println("Ex4 filmsTriesParTitre first = " + (filmsTriesParTitre.isEmpty() ? "none" : filmsTriesParTitre.get(0).getTitre()));
		//System.out.println("Ex5 filmsTriesParDureeDesc first = " + (filmsTriesParDureeDesc.isEmpty() ? "none" : filmsTriesParDureeDesc.get(0).getTitre()));
		//System.out.println("Ex6 filmLePlusLong = " + (filmLePlusLong == null ? "null" : filmLePlusLong.getTitre()));
		//System.out.println("Ex7 dureeMaxWatchlistU1 = " + dureeMaxWatchlistU1);
		//System.out.println("Ex8 nbFilmsCriteres = " + nbFilmsCriteres);
		//System.out.println("Ex9 tousOntUneWatchlistNonVide = " + tousOntUneWatchlistNonVide);
		//System.out.println("Ex9 auMoinsUnPremium = " + auMoinsUnPremium);
		//System.out.println("Ex9 aucunEmailGmail = " + aucunEmailGmail);


	}

}
