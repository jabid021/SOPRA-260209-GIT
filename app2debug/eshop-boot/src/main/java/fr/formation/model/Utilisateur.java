package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "[user]")
public class Utilisateur {
    @Id
    @UuidGenerator
    @Column(name = "usr_id")
    private String id;

    @Column(name = "usr_username", length = 50, nullable = false)
    private String username;

    @Column(name = "usr_password", length = 150, nullable = false)
    private String password;

    @Column(name = "usr_email", length = 150, nullable = false)
    private String email;

    @Column(name = "usr_is_admin")
    private boolean admin;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
