package hopital.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import hopital.context.Singleton;
import hopital.dao.IDAOCompte;
import hopital.dao.IDAOPatient;
import hopital.dao.IDAOVisite;
import hopital.model.Compte;
import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Secretaire;
import hopital.model.Visite;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

	static File fichier = new File("fileAttente.txt");
	static LinkedList<Patient> fileAttente = new LinkedList();
	static boolean pause = false;
	static Compte connected;
	
	static IDAOCompte daoCompte = Singleton.getInstance().getDaoCompte();
	static IDAOVisite daoVisite = Singleton.getInstance().getDaoVisite();
	static IDAOPatient daoPatient = Singleton.getInstance().getDaoPatient();
	
	public static IDAOCompte getDaoCompte() {
		return daoCompte;
	}

	public static void setDaoCompte(IDAOCompte daoCompte) {
		App.daoCompte = daoCompte;
	}
	
	

	public static int saisieInt(String message) 
	{
		Scanner monScanner = new Scanner(System.in); 
		System.out.println(message);
		return monScanner.nextInt();
	}

	public static String saisieString(String message) 
	{
		Scanner monScanner = new Scanner(System.in); 
		System.out.println(message);
		return monScanner.nextLine();
	}



	public static void menuPrincipal() {
		System.out.println("--------Menu principal de l'hopital-------");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Quitter l'app");
		int choix = saisieInt("Choisir un menu");


		switch(choix) 
		{
		case 1 : seConnecter();break;
		case 2 : System.exit(0);break;
		}
		menuPrincipal();
	}


	public static void seConnecter() {

		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected = daoCompte.findByLoginAndPassword(login, password);

		if(connected==null) 
		{
			System.out.println("Identifiants invalides");
		}
		else if(connected instanceof Medecin) 
		{
			int salle = saisieInt("Dans quelle salle s'installer ?");
			((Medecin) connected).setSalle(salle);
			menuMedecin();
		}
		else if(connected instanceof Secretaire) 
		{
			if(pause==true) 
			{
				menuSecretairePause();
			}
			else 
			{
				menuSecretaire();
			}
		}
	}

	public static void menuSecretaire() {
		System.out.println("\n-----Menu Secretaire------");
		System.out.println("1 - Accueillir patient");
		System.out.println("2 - Afficher les anciennes visites d'un patient");
		System.out.println("3 - Afficher l'etat de la file d'attente");
		System.out.println("4 - Supprimer un patient de notre bdd");
		System.out.println("5 - Partir en pause");
		System.out.println("6 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : accueillirPatient();break;
		case 2 : afficherVisites();break;
		case 3 : afficherFileAttente();break;
		case 4 : supprimerPatient();break;
		case 5 : partirEnPause();break;
		case 6 : seDeconnecter();break;
		}
		menuSecretaire();
	}


	public static void seDeconnecter() {
		connected=null;
		menuPrincipal();
	}

	public static void partirEnPause() {

		//On peut mettre les objets à open / close dans les parenthes du try(xxxx){}catch()
		//ces objets sont auto close à la fin du try
		try(
				FileOutputStream fos = new FileOutputStream(fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				) 
		{
			oos.writeObject(fileAttente);
			fileAttente.clear();
			pause=true;
			menuSecretairePause();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void supprimerPatient() {
		System.out.println("Voici la liste des patients :");
		for(Patient p  : daoPatient.findAll()) 
		{
			System.out.println(p);
		}
		
		int id = saisieInt("Saisir l'id du patient à supprimer");
		//
		/*List<Visite> visitesDuPatient = daoVisite.findByPatientId(id);
		for(Visite v : visitesDuPatient) 
		{
			v.setPatient(null);
			daoVisite.save(v);
		}*/
		daoPatient.deleteById(id);
	}

	public static void afficherFileAttente() {
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}
		else 
		{
			for(Patient p : fileAttente) 
			{
				System.out.println(p);
			}

			if(connected instanceof Medecin) 
			{
				System.out.println("Le prochain patient sera : "+fileAttente.peekFirst());
			}
		}
	}

	public static void afficherVisites() {
		int id = saisieInt("Saisir l'id du patient");
		List<Visite> visites = daoVisite.findByPatientId(id);
		if(daoPatient.findById(id)==null) 
		{
			System.out.println("Ce patient n'existe pas");
		}
		else 
		{
			if(visites.isEmpty()) {System.out.println("Ce patient n'a pas encore de visite");}
			for(Visite v : visites) 
			{
				System.out.println(v);
			}
		}
	}

	public static void accueillirPatient() {
		int id = saisieInt("Saisir l'id du patient");
		Patient patient = daoPatient.findById(id);
		if(patient==null) 
		{
			System.out.println("Creation d'un nouveau patient !");
			String prenom = saisieString("Saisir votre prenom");
			String nom = saisieString("Saisir votre nom");
			patient = new Patient(id,prenom,nom);
			daoPatient.save(patient);
		}
		System.out.println("Le patient "+patient+" est ajouté dans la file d'attente");
		fileAttente.add(patient);
	}

	public static void menuSecretairePause() {
		System.out.println("\n-----Menu Secretaire (En pause)------");
		System.out.println("1 - Revenir de pause");
		System.out.println("2 - Se deconnecter");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : revenirPause();break;
		case 2 : seDeconnecter();break;
		}
		menuSecretairePause();
	}


	public static void revenirPause() {
		try
		{
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			fileAttente = (LinkedList<Patient>) ois.readObject();
			pause=false;
			ois.close();
			fis.close();
			menuSecretaire();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void menuMedecin() {
		System.out.println("\n-----Menu Medecin------");
		System.out.println("1 - Recevoir un patient");
		System.out.println("2 - Voir l'etat de la file d'attente");
		System.out.println("3 - Sauvegarder mes visites");
		System.out.println("4 - Consulter mon CA");
		System.out.println("5 - Se deconnecter");
		
		int choix = saisieInt("Choisir un menu");
		
		switch(choix) 
		{
		case 1 : recevoirPatient();break;
		case 2 : afficherFileAttente();break;
		case 3 : saveVisites();break;
		case 4 : consulterCA();break;
		case 5 : seDeconnecter();break;
		}
		menuMedecin();
	}
	
	
	public static void recevoirPatient() {
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}
		else 
		{
			Medecin medecin = (Medecin) connected;
			Patient prochainPatient = fileAttente.pollFirst();
			Visite visite = new Visite(prochainPatient,medecin);
			medecin.getConsultations().add(visite);
			if(medecin.getConsultations().size()>=3) 
			{
				System.out.println("Cumul de 3 visites, save auto !");
				saveVisites();
			}		
		}
	}

	public static void saveVisites() {
		Medecin medecin = (Medecin) connected;
		if(medecin.getConsultations().isEmpty()) 
		{
			System.out.println("Vous n'avez pas de visite à save");
		}
		else 
		{
			for(Visite v : medecin.getConsultations()) 
			{
				v=daoVisite.save(v);
				System.out.println("Sauvegarde de la visite "+v);
			}
			medecin.getConsultations().clear();
		}
	}

	public static void consulterCA() {
		 String debut = saisieString("Saisir le debut de la periode");
	        String fin =saisieString("Saisir la fin de la periode");
	        List<Visite> visites = daoVisite.findByDateVisiteBetweenAndMedecinId(LocalDate.parse(debut),LocalDate.parse(fin),connected.getId());

	        double total = 0;
	        for (Visite v : visites) {
	            System.out.println(v);
	            total+=v.getPrix();
	        }
	        
	        total=daoVisite.sumPrixByDateVisiteBetweenAndMedecinId(LocalDate.parse(debut),LocalDate.parse(fin),connected.getId());
	        
	        total = visites.stream().mapToDouble(v->v.getPrix()).sum();
	        
	        
	        System.out.println("Votre CA sur la période du "+debut+" au "+fin+" est de "+total+"€");
	        System.out.println("(Calcul SUM JPA) Votre CA sur la période du "+debut+" au "+fin+" est de "+total+"€");
	}

	public static void main(String[] args) {
		menuPrincipal();
		Singleton.getInstance().getEmf().close();
	}

}
