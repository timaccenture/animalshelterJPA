package com.accenture.AnimalShelterJPA.data.mapper;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import org.springframework.stereotype.Component;

@Component
public class AnimalDtoToEntityMapper implements Mapper<AnimalRecordDto, AnimalEntity>{

    @Override
    public AnimalEntity mapTo(AnimalRecordDto animalRecordDto) {
        AnimalEntity target = new AnimalEntity();
        target.setId(animalRecordDto.id());
        target.setName(animalRecordDto.name());
        target.setSpecies(animalRecordDto.species());
        target.setAge(animalRecordDto.age());
        target.setAdopted(animalRecordDto.isAdopted());
        return target;
    }
}
