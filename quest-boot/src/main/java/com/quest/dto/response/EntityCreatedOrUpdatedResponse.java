package com.quest.dto.response;

public class EntityCreatedOrUpdatedResponse {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntityCreatedOrUpdatedResponse(int id) {
        this.id = id;
    }
}
