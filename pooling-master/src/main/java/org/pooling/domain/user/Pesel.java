package org.pooling.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Pesel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 11, max = 11)
    @Pattern(regexp = "^[0-9]*$", message = "PESEL number must contain only digits")
    private String PESEL;

    @JsonBackReference
    @OneToOne(mappedBy = "pesel")
    private AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

}
