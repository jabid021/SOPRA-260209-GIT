package banque.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {

	protected final int numero;
	protected double solde;
	protected double taxe;
	protected Carte carte;
	
	private static int nbTransac;
	private static double totalTaxe;
	private static List<Compte> banque = new ArrayList();
	
	
	public Compte(double solde, double taxe, Carte carte) {
		banque.add(this);
		numero=banque.size();
		this.solde = solde - carte.getPrix();
		this.taxe = taxe;
		this.carte = carte;
	}


	public double getSolde() {
		return solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}


	public double getTaxe() {
		return taxe;
	}


	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}


	public Carte getCarte() {
		return carte;
	}


	public void setCarte(Carte carte) {
		this.carte = carte;
	}


	public static int getNbTransac() {
		return nbTransac;
	}


	public static void setNbTransac(int nbTransac) {
		Compte.nbTransac = nbTransac;
	}


	public static double getTotalTaxe() {
		return totalTaxe;
	}


	public static void setTotalTaxe(double totalTaxe) {
		Compte.totalTaxe = totalTaxe;
	}


	public static List<Compte> getBanque() {
		return banque;
	}


	public static void setBanque(List<Compte> banque) {
		Compte.banque = banque;
	}


	public int getNumero() {
		return numero;
	}
	
	protected abstract boolean verif(double montant); 
	
	public void retrait(double montant) 
	{
		if(verif(-montant)) 
		{
			this.solde=this.solde-montant-taxe;
			nbTransac++;
			totalTaxe+= taxe;
			System.out.println("Le retrait de "+montant+"euros est effectué");
		}
		else 
		{
			System.out.println("Le retrait de "+montant+"euros est refusé");
		}
	}
	
	public void depot(double montant) 
	{
		if(verif(montant)) 
		{
			this.solde=this.solde+montant-taxe;
			nbTransac++;
			totalTaxe+= taxe;
			System.out.println("Le depot de "+montant+"euros est effectué");
		}
		else 
		{
			System.out.println("Le depot de "+montant+"euros est refusé");
		}
	}
	
	public void transfert(double montant, Compte destinataire) {
		if(this.verif(-montant) && destinataire.verif(montant)) 
		{
			this.retrait(montant);
			destinataire.depot(montant);
		}
		else 
		{
			System.out.println("Le transfert de "+montant+"euros est refusé");
		}
	}
	
	
	
}
