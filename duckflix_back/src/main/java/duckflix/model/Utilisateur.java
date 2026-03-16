package duckflix.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("user")
public class Utilisateur extends Compte {
	@Column(name="mail",unique = true,length = 50)
	private String email;
	
	@OneToOne
	private Abonnement abonnement;
	
	//ManyToMany va generer une table de jointure avec l'id de la classe ou on se trouve (Client) + l'id de la classe associée (Media)
	//1 (client) - 1(media)
	//1 (client) - 5 (media)
	
	//(client) => id principal
	//(media) => la cle inverse
	@ManyToMany
	@JoinTable(
			name="watchlist",
			joinColumns = @JoinColumn(name="client"),
			inverseJoinColumns =  @JoinColumn(name="media"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"client","media"})
			)
	private List<Media> watchlist = new ArrayList();

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
