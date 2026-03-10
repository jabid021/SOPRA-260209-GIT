package duckflix.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Virement extends Paiement {

	@Column(nullable = false,length = 60)
	private String iban;
	@Column(nullable = false)
	private String reference;
	
	public Virement() {}
	
	public Virement(LocalDate datePaiement, String iban, String reference) {
		super(datePaiement);
		this.iban = iban;
		this.reference = reference;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Virement [id=" + id + ", datePaiement=" + datePaiement + ", iban=" + iban + ", reference=" + reference
				+ "]";
	}
	
	
}
