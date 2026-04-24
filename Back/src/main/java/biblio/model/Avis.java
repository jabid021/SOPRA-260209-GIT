package biblio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="avis")
public class Avis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "note", nullable = false)
	private Integer note;
	
	@Column(name = "commentaire", nullable = true)
	private String commentaire;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="livre",nullable=false)
	private Livre livre;
	
	public Avis() {}
	
	public Avis(Integer note, String commentaire, LocalDate date, Livre livre) {
		this.note = note;
		this.commentaire = commentaire;
		this.date = date;
		this.livre = livre;
	}

	public Avis(Integer id, Integer note, String commentaire, LocalDate date, Livre livre) {
		this.id = id;
		this.note = note;
		this.commentaire = commentaire;
		this.date = date;
		this.livre = livre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	@Override
	public String toString() {
		return "Avis [id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", date=" + date + ", livre="
				+ livre + "]";
	}
}
