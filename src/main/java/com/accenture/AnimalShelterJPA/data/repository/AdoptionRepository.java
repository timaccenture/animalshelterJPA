package com.accenture.AnimalShelterJPA.data.repository;

import com.accenture.AnimalShelterJPA.data.model.AdoptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, Long> {
}
