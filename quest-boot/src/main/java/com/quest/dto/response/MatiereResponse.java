package com.quest.dto.response;

import com.quest.model.Matiere;

public class MatiereResponse {
    private int id;
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public static MatiereResponse convert(Matiere matiere) {
        MatiereResponse response = new MatiereResponse();

        response.setId(matiere.getId());
        response.setLibelle(matiere.getLibelle());

        return response;
    }

}
