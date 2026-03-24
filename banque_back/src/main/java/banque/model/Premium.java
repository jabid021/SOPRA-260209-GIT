package banque.model;

public class Premium extends Compte {

	public Premium(double solde,Carte carte) {
		super(solde-150, 0, carte);
	}

	@Override
	protected boolean verif(double montant) {
		return true;
	}

	@Override
	public String toString() {
		return "Premium [numero=" + numero + ", solde=" + solde + ", taxe=" + taxe + ", carte=" + carte + "]";
	}
	
	

}
