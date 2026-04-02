package fr.formation.config;

public enum RoleEnum {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String role;

    public String getRole() {
        return role;
    }

    private RoleEnum(String role) {
        this.role = role;
    }
}
