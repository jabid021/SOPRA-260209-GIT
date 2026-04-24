package biblio.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false, unique = true, length = 30)
    private String nom;

    @Column(name = "pays", nullable = false, length = 30)
    private String pays;

    /*@OneToMany
    @JoinColumn(name="livre", nullable = false)
	private List<Livre> livre;*/

    public Editeur() {}

    public Editeur(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
    }

    public Editeur(Integer id, String nom, String pays) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPays() {
        return pays;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Editeur [id=" + id + ", nom=" + nom + ", pays=" + pays + "]";
    }

}