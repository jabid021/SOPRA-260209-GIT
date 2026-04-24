package biblio.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name ="auteur_Id")
    private Auteur auteur;

    @ManyToOne
    @JoinColumn(name = "editeur_Id")
    private Editeur editeur;

    @ManyToOne
    @JoinColumn(name = "collection_Id")
    private Collection collection;

    @OneToMany(mappedBy = "livre")
    private List<Avis> avis;

    public Livre(){}

    public Livre(String titre, String resume, LocalDate annee, Editeur editeur, Collection collection){
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.editeur = editeur;
        this.collection = collection;
    }

    public Livre(Integer id, String titre, String resume, LocalDate annee, Editeur editeur, Collection collection){
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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
