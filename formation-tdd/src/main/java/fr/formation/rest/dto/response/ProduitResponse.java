package fr.formation.rest.dto.response;

import fr.formation.model.Produit;

public class ProduitResponse {
    private int id;
    private String nom;

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

    public static ProduitResponse convert(Produit p) {
        ProduitResponse resp = new ProduitResponse();

        resp.setId(p.getId());
        resp.setNom(p.getNom());

        return resp;
    }
}
