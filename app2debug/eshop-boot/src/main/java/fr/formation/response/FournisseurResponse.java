package fr.formation.response;

import fr.formation.model.Fournisseur;

public class FournisseurResponse {
    private int id;
    private String nom;
    private int produitsCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getProduitsCount() {
        return produitsCount;
    }

    public void setProduitsCount(int produitsCount) {
        this.produitsCount = produitsCount;
    }

    public static FournisseurResponse convert(Fournisseur fournisseur) {
        FournisseurResponse response = new FournisseurResponse();

        response.id = fournisseur.getId();
        response.nom = fournisseur.getNom();

        if (fournisseur.getProduits() != null) {
            response.produitsCount = fournisseur.getProduits().size();
        }

        return response;
    }
}
