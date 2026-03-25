package banque.model;

public class Payant extends Compte{

	private double decouvert;
	
	public Payant(double solde, Carte carte,double decouvert) {
		super(solde-50, 1, carte);
		this.decouvert= Math.abs(decouvert);
	}

	@Override
	protected boolean verif(double montant) {
		if(this.solde+montant-taxe+decouvert>=0) {return true;}
		else {return false;}
	}
	
	

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public String toString() {
		return "Payant [numero=" + numero + ", solde=" + solde + ", taxe=" + taxe + ", carte=" + carte + ", decouvert="
				+ decouvert + "]";
	}


	
}
