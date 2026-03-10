package duckflix.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Carte extends Paiement {

	@Column(nullable = false,length = 4)
	private String dernier4;
	private String typeCarte;
	
	public Carte() {}
	
	

	public Carte(LocalDate datePaiement, String dernier4, String typeCarte) {
		super(datePaiement);
		this.dernier4 = dernier4;
		this.typeCarte = typeCarte;
	}

	public String getDernier4() {
		return dernier4;
	}

	public void setDernier4(String dernier4) {
		this.dernier4 = dernier4;
	}

	public String getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}



	@Override
	public String toString() {
		return "Carte [id=" + id + ", datePaiement=" + datePaiement + ", dernier4=" + dernier4 + ", typeCarte="
				+ typeCarte + "]";
	}
	
	
}
