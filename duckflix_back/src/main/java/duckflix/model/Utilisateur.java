package duckflix.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("user")
public class Utilisateur extends Compte {
	@Column(name="mail",unique = true,length = 50)
	private String email;
	private transient Abonnement abonnement;
	private transient List<Media> watchlist = new ArrayList();

	public Utilisateur() {}
	
	public Utilisateur(Integer id, String login, String password,String email, Abonnement abonnement) {
		super(id, login, password);
		this.email=email;
		this.abonnement = abonnement;

	}

	public Utilisateur(String login, String password,String email, Abonnement abonnement) {
		super(login, password);
		this.email=email;
		this.abonnement = abonnement;
	}

	public Abonnement getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public List<Media> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(List<Media> watchlist) {
		this.watchlist = watchlist;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email
				+ ", abonnement=" + abonnement + "]";
	}

	

}
