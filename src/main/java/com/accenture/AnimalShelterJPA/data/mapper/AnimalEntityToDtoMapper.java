package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import org.springframework.stereotype.Component;

@Component
public class AnimalEntityToDtoMapper implements Mapper<AnimalEntity, AnimalRecordDto>{
    @Override
    public AnimalRecordDto mapTo(AnimalEntity animal) {
        return new AnimalRecordDto(
                animal.getId(), animal.getName(),
                animal.getSpecies(), animal.getAge(), animal.isAdopted()
        );
    }
}
