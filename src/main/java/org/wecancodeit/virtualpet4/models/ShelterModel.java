package org.wecancodeit.virtualpet4.models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;

@Entity
public class ShelterModel {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "shelter",cascade = CascadeType.ALL)
    private Collection<PetModel> pets;

    public ShelterModel() {
    }

    public ShelterModel(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @JsonIgnore
    public Collection<PetModel> getPets() {
        return pets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
