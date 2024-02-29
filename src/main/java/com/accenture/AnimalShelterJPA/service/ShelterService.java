package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import com.accenture.AnimalShelterJPA.data.model.ShelterEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterRecordDto;
import com.accenture.AnimalShelterJPA.data.repository.ShelterRepository;

import java.util.List;

public interface ShelterService {

    void create(ShelterRecordDto shelter) throws Exception;
    List<ShelterRecordDto> findAll();
    ShelterRecordDto getById(Long id);
    void deleteById(Long id);
    ShelterRecordDto updateById(Long id, ShelterRecordDto shelter);
    void addAnimals(Long id, List<AnimalRecordDto> animals);
}
