package org.pooling.domain.user;

import jakarta.persistence.*;

@Entity
@Table(name = "appuserrole")
public class AppUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String role;

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
