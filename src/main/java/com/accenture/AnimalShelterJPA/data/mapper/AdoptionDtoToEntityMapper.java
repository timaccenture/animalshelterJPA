package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AdoptionEntity;
import com.accenture.AnimalShelterJPA.data.model.AdoptionRecordDto;
import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.repository.AdoptionRepository;
import com.accenture.AnimalShelterJPA.data.repository.AnimalRepository;
import org.springframework.stereotype.Component;

@Component
public class AdoptionDtoToEntityMapper implements Mapper<AdoptionRecordDto, AdoptionEntity>{

    public final AnimalRepository animalRepository;
    public AdoptionDtoToEntityMapper(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AdoptionEntity mapTo(AdoptionRecordDto adoptionRecordDto) {
        AdoptionEntity adoptionEntity = new AdoptionEntity();
        adoptionEntity.setId(adoptionRecordDto.id());
        adoptionEntity.setAdopterName(adoptionRecordDto.adopterName());
        adoptionEntity.setAdoptionDate(adoptionRecordDto.adoptionDate());
        AnimalEntity animalEntity = animalRepository.findById(adoptionRecordDto.adoptedAnimalId()).orElseThrow(() -> new RuntimeException("has to be type adoptionRecordDto"));
        adoptionEntity.setAdoptedAnimal(animalEntity); // hier bisschen logic, sodass animalentity übergeben wird
        return adoptionEntity;
    }
}
