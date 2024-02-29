package com.accenture.AnimalShelterJPA.data.repository;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    @Query("select a from AnimalEntity a")
    List<AnimalEntity> findAllwithJPQL();
    @Query("select a from AnimalEntity a where a.id = ?1")
    AnimalEntity findAnimalByIdJPQL(Long id);
}
