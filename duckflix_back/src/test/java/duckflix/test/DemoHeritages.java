package duckflix.test;

import duckflix.context.Singleton;
import duckflix.dao.IDAOCompte;
import duckflix.dao.IDAOMedia;
import duckflix.dao.IDAOPaiement;

public class DemoHeritages {

	public static void main(String[] args) {
	
		IDAOCompte daoCompte = Singleton.getInstance().getDaoCompte();
		
		IDAOMedia daoMedia = Singleton.getInstance().getDaoMedia();
		
		IDAOPaiement daoPaiement = Singleton.getInstance().getDaoPaiement();
		
		
		
		
		
		/*System.out.println("--------DEMO REQUETES COMPTES (SINGLETABLE)");
		System.out.println("TOUS LES COMPTES : ");
		daoCompte.findAll();
		System.out.println("TOUS LES UTILISATEURS : ");
		daoCompte.findAllUtilisateur();
		
		*/
		
		//----------------
		
		/*System.out.println("--------DEMO REQUETES MEDIAS (JOINED)");
		
		System.out.println("TOUS LES MEDIAS : ");
		daoMedia.findAll();
		System.out.println("TOUS LES FILMS : ");
		daoMedia.findAllFilm();
		
		*/
		//----------------
		
		
		System.out.println("--------DEMO REQUETES PAIEMENT (TABLE PER CLASS)");
		
		System.out.println("TOUS LES PAIEMENT : ");
		daoPaiement.findAll();
		System.out.println("TOUS LES PAYPAL : ");
		daoPaiement.findAllPaypal();
		
	}

}
