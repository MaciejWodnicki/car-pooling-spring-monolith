package org.pooling.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Size(min=6, max=6)
    @Column(name = "zip", nullable = false)
    private String zip;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "country", nullable = false)
    private String country;

    //@OneToMany(mappedBy = "address")
    //private List<AppUser> appUserList;

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*
    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

     */
}
