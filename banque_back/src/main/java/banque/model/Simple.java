package banque.model;

public class Simple extends Compte{

	public Simple(double solde, Carte carte) {
		super(solde, 5, carte);
	}

	@Override
	protected boolean verif(double montant) {
		if(this.solde+montant-taxe>=0) {return true;}
		else {return false;}
	}

	@Override
	public String toString() {
		return "Simple [numero=" + numero + ", solde=" + solde + ", taxe=" + taxe + ", carte=" + carte + "]";
	}

	
}
