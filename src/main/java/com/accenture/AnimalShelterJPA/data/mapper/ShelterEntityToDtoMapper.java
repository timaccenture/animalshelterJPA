package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterRecordDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShelterEntityToDtoMapper  implements Mapper<ShelterEntity, ShelterRecordDto>{

    @Override
    public ShelterRecordDto mapTo(ShelterEntity shelterEntity) {
        // map list of animalentities into list with its corresp id
        return new ShelterRecordDto(
                shelterEntity.getId(), shelterEntity.getName(), shelterEntity.getAddress(),
                shelterEntity.getCapacity(), shelterEntity.getAnimals().stream().map(AnimalEntity::getId).toList());
    }
}
