package fr.formation.serviceclient.api.dto.response;

import fr.formation.serviceclient.model.Client;

public record ClientResponse(String id, String nom, String prenom) {
    public static ClientResponse convert(Client client) {
        return new ClientResponse(client.getId(), client.getNom(), client.getPrenom());
    }
}
