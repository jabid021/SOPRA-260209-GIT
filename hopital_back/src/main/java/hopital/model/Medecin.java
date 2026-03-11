package hopital.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("Medecin")
public class Medecin extends Compte {

	private transient List<Visite> consultations = new ArrayList();
	private transient int salle;

	public Medecin() {}
	
	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
	}

	public List<Visite> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Visite> consultations) {
		this.consultations = consultations;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", login=" + login + ", password=" + password + ", salle=" + salle + "]";
	}
	
	
	
}
