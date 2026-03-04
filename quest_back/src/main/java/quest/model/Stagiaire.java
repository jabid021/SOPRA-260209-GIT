package quest.model;

public class Stagiaire extends Personne {

	private String email;
	private Adresse adresse;
	private Filiere filiere;
	
	public Stagiaire(Integer id, String login, String password, String nom, String prenom, Genre civilite, String email,String numero,String voie,String ville,String cp,Filiere filiere) {
		super(id, login, password, nom, prenom,civilite);
		this.email = email;
		this.filiere = filiere;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}
	
	public Stagiaire(String login, String password, String nom, String prenom, Genre civilite, String email,String numero,String voie,String ville,String cp,Filiere filiere) {
		super(login, password, nom, prenom,civilite);
		this.email = email;
		this.filiere = filiere;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", email=" + email + ", adresse=" + adresse + ", filiere="
				+ filiere + "]";
	}

	
	
	
}
