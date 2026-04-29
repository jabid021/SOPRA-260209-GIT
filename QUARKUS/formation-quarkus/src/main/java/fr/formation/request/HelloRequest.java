package fr.formation.request;

import jakarta.ws.rs.QueryParam;

public class HelloRequest {
    @QueryParam("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
