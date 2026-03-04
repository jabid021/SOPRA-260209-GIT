package quest.model;

import java.time.LocalDate;

public class Module {

	private Integer id;
	private int quest;
	private LocalDate debut;
	private LocalDate fin;
	private Filiere filiere;
	private Matiere matiere;
	
	public Module(Integer id, int quest, LocalDate debut, LocalDate fin, Filiere filiere, Matiere matiere) {
		this.id = id;
		this.quest = quest;
		this.debut = debut;
		this.fin = fin;
		this.filiere = filiere;
		this.matiere = matiere;
	}
	
	public Module(int quest, LocalDate debut, LocalDate fin, Filiere filiere, Matiere matiere) {
		this.quest = quest;
		this.debut = debut;
		this.fin = fin;
		this.filiere = filiere;
		this.matiere = matiere;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuest() {
		return quest;
	}

	public void setQuest(int quest) {
		this.quest = quest;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", quest=" + quest + ", debut=" + debut + ", fin=" + fin + ", filiere=" + filiere
				+ ", matiere=" + matiere + "]";
	}
	
	
}
