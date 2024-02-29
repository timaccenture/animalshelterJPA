package com.accenture.AnimalShelterJPA.data.repository;

import com.accenture.AnimalShelterJPA.data.model.ShelterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterEntity, Long> {
}
