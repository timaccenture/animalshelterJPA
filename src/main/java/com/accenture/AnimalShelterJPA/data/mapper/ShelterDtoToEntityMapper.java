package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterRecordDto;
import com.accenture.AnimalShelterJPA.data.repository.AnimalRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShelterDtoToEntityMapper implements Mapper<ShelterRecordDto, ShelterEntity>{

    public final AnimalRepository animalRepository;

    public ShelterDtoToEntityMapper(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public ShelterEntity mapTo(ShelterRecordDto shelterRecordDto) {
        ShelterEntity shelterEntity = new ShelterEntity();
        shelterEntity.setId(shelterRecordDto.id());
        shelterEntity.setName(shelterRecordDto.name());
        shelterEntity.setAddress(shelterRecordDto.address());
        shelterEntity.setCapacity(shelterRecordDto.capacity());
        // mapped list of id to list of corresp animalentities
        shelterEntity.setAnimals(
            shelterRecordDto.animal_ids().stream()
                    .map((id) -> animalRepository.findById(id).orElse(null)).toList()
        );
        return shelterEntity;
    }
}
