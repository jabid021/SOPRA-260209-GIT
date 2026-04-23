
@Entity
@Table(name="auteur")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nom", nullable = false,length = 30)
    private String nom;

    @Column(name="prenom", nullable = false,length = 30)
    private String prenom;

    @Column(name="nationalite", nullable = false,length = 30)
    private String nationalite;

    @OneToMany
    @JoinColumn(name="livre", nullable = false,length = 30)
    private Livre livres;

    public Auteur(){}

    public Auteur(String nom, String prenom, String nationalite, Livre livres){
        this.nom=nom;
        this.prenom=prenom;
        this.nationalite=nationalite;
        this.livres=livres;
    }

    public Auteur(Integer id, String nom, String prenom, String nationalite, Livre livres){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.nationalite=nationalite;
        this.livres=livres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getNom() {
        return nom;
    }

    public void setNom(Integer id) {
        this.id = nom;
    }
    
    public Integer getPrenom() {
        return prenom;
    }

    public void setPrenom(Integer id) {
        this.id = prenom;
    }
    
    public Integer getNationalite() {
        return Nationalite;
    }

    public void setNationalite(Integer id) {
        this.id = nationalite;
    }

    public Integer getLivres() {
        return livres;
    }

    public void setLivres(Integer id) {
        this.id = livres;
    }
}
