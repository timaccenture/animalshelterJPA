package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.model.AdoptionRecordDto;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;

import java.util.List;

public interface AdoptionService {

    void create(AdoptionRecordDto adoption);
    List<AdoptionRecordDto> findAll();
    AdoptionRecordDto getById(Long id);
    void deleteById(Long id);
    AdoptionRecordDto updateById(Long id, AdoptionRecordDto adoptionRecordDto);
}
