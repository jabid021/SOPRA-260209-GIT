package duckflix.model;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur extends Compte {
	private Abonnement abonnement;
	private List<Media> watchlist = new ArrayList();

	public Utilisateur(Integer id, String login, String password, Abonnement abonnement) {
		super(id, login, password);
		this.abonnement = abonnement;

	}

	public Utilisateur(String login, String password, Abonnement abonnement) {
		super(login, password);
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

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", abonnement=" + abonnement
				+ "]";
	}

}
