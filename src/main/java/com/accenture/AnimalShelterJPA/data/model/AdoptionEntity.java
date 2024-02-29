package com.accenture.AnimalShelterJPA.data.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class AdoptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String adopterName;
    private String adopterAddress;
    private LocalDate adoptionDate;
    @OneToOne
    private AnimalEntity adoptedAnimal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getAdopterAddress() {
        return adopterAddress;
    }

    public void setAdopterAddress(String adopterAddress) {
        this.adopterAddress = adopterAddress;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public AnimalEntity getAdoptedAnimal() {
        return adoptedAnimal;
    }

    public void setAdoptedAnimal(AnimalEntity adoptedAnimal) {
        this.adoptedAnimal = adoptedAnimal;
    }
}
