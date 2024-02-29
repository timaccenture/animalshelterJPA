package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AdoptionEntity;
import com.accenture.AnimalShelterJPA.data.model.AdoptionRecordDto;
import org.springframework.stereotype.Component;

@Component
public class AdoptionEntityToDtoMapper implements Mapper<AdoptionEntity, AdoptionRecordDto>{

    @Override
    public AdoptionRecordDto mapTo(AdoptionEntity adoptionEntity) {
        return new AdoptionRecordDto(
                adoptionEntity.getId(), adoptionEntity.getAdopterName(),
                adoptionEntity.getAdoptionDate(), adoptionEntity.getAdoptedAnimal().getId()
        );
    }
}
