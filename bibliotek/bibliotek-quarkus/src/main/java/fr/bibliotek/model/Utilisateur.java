package fr.bibliotek.model;

import org.hibernate.annotations.UuidGenerator;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
@UserDefinition
public class Utilisateur {
    @Id
    @UuidGenerator
    private String id;

    @Column(length = 50, nullable = false, unique = true)
    @Username
    private String username;

    @Column(length = 150, nullable = false)
    @Password
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Roles
    public String getRole() {
        return "user";
    }
}
