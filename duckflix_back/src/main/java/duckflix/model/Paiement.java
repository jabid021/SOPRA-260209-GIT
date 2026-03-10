package duckflix.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seqJava", sequenceName = "seq_paiement_bdd")
public abstract class Paiement {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqJava")
	protected Integer id;
	@Column(nullable = false,name="date_paiement")
	protected LocalDate datePaiement;
	
	public Paiement() {}

	public Paiement(LocalDate datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(LocalDate datePaiement) {
		this.datePaiement = datePaiement;
	}
	
	
}
