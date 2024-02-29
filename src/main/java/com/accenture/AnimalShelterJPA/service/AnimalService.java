package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    void create(AnimalRecordDto animal);

    List<AnimalRecordDto> findAll();

    AnimalRecordDto getById(Long id);

    void deleteById(Long id);

    AnimalRecordDto updateById(Long id, AnimalRecordDto animal);

    List<AnimalEntity> findAllWithJPQL();

    AnimalEntity findAnimalById(Long id);
}
