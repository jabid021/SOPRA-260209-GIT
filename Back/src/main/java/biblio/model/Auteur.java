//Audric

package biblio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="auteur")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nom", nullable = false, length = 30)
    private String nom;

    @Column(name="prenom", nullable = false, length = 30)
    private String prenom;

    @Column(name="nationalite", nullable = false, length = 30)
    private String nationalite;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    public Auteur() {}

    public Auteur(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public Auteur(Integer id, String nom, String prenom, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getNom() { 
        return nom; 
    }
    public void setNom(String nom) { 
        this.nom = nom; 
    }

    public String getPrenom() { 
        return prenom; 
    }
    public void setPrenom(String prenom) { 
        this.prenom = prenom; 
    }

    public String getNationalite() { 
        return nationalite; 
    }
    public void setNationalite(String nationalite) { 
        this.nationalite = nationalite; 
    }

    public List<Livre> getLivres() { 
        return livres; 
    }
    public void setLivres(List<Livre> livres) { 
        this.livres = livres; 
    }
}