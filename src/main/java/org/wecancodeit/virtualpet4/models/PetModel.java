package org.wecancodeit.virtualpet4.models;

import org.wecancodeit.virtualpet4.enums.PetTypeEnum;

import jakarta.persistence.*;

@Entity
public class PetModel {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private PetTypeEnum petType;

    private int age;

    @Transient
    private long shelter_id;

    @ManyToOne
    private ShelterModel shelter;

    public PetModel() {
    }

    public PetModel(String name, PetTypeEnum petType, int age, ShelterModel shelter) {
        this.name = name;
        this.petType = petType;
        this.age = age;
        this.shelter = shelter;
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

    public PetTypeEnum getPetType() {
        return petType;
    }

    public void setPetType(PetTypeEnum petType) {
        this.petType = petType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ShelterModel getShelter() {
        return shelter;
    }

    public long getShelter_id() {
        if (this.shelter == null) {
            return shelter_id;
        } else {
            return this.shelter.getId();
        }
    }
}