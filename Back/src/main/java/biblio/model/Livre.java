package biblio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="titre", nullable = false,length = 30)
    private String titre;

    @Column(name="resume", nullable = false,length = 255)
    private String resume;

    @Column(name="annee", nullable = false)
    private LocalDate annee;

    @ManyToOne
    @Column(name="auteur", nullable = false)
    private Auteur auteur;

    @ManyToOne
    @JoinColumn(name = "editeurId")
    private Editeur editeur;

    @Column(name="collection", nullable = true,length = 30)
    private String collection;

    public Livre(){}

    public Livre(String titre, String resume, LocalDate annee, Editeur editeur, String collection){
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.editeur = editeur;
        this.collection = collection;
    }

    public Livre(Integer id, String titre, String resume, LocalDate annee, Editeur editeur, String collection){
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.editeur = editeur;
        this.collection = collection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getAnnee() {
        return annee;
    }

    public void setAnnee(LocalDate annee) {
        this.annee = annee;
    }

    public Editeur getEditeur() {return editeur;}

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
