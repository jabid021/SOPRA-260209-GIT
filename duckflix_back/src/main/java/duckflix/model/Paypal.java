package duckflix.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Paypal extends Paiement {

	@Column(nullable = false,length = 50)
	private String emailPaypal;
	
	public Paypal() {}

	public Paypal(LocalDate datePaiement, String emailPaypal) {
		super(datePaiement);
		this.emailPaypal = emailPaypal;
	}

	public String getEmailPaypal() {
		return emailPaypal;
	}

	public void setEmailPaypal(String emailPaypal) {
		this.emailPaypal = emailPaypal;
	}

	@Override
	public String toString() {
		return "Paypal [id=" + id + ", datePaiement=" + datePaiement + ", emailPaypal=" + emailPaypal + "]";
	}
	
	
	
}
