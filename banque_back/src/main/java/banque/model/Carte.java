package banque.model;

public enum Carte {

	CB(0),Visa(5),Mastercard(10);
	
	private double prix;

	private Carte(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
