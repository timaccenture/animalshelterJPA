package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.mapper.AnimalDtoToEntityMapper;
import com.accenture.AnimalShelterJPA.data.mapper.AnimalEntityToDtoMapper;
import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import com.accenture.AnimalShelterJPA.data.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    public final AnimalRepository animalRepository;
    public final AnimalDtoToEntityMapper dtoMapper;
    public final AnimalEntityToDtoMapper entityMapper;
    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalDtoToEntityMapper dtoMapper, AnimalEntityToDtoMapper entityMapper) {
        this.animalRepository = animalRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public void create(AnimalRecordDto animalRecordDto) {
        AnimalEntity animalEntity = dtoMapper.mapTo(animalRecordDto);
        animalRepository.save(animalEntity);
    }

    @Override
    public AnimalRecordDto updateById(Long id, AnimalRecordDto animalRecordDto) {
        AnimalEntity animalEntity = dtoMapper.mapTo(animalRecordDto);
        animalEntity.setId(id);
        return entityMapper.mapTo(animalRepository.save(animalEntity));
    }

    @Override
    public List<AnimalRecordDto> findAll() {
        List<AnimalEntity> animals = animalRepository.findAll();
        return animals.stream().sorted(Comparator.comparing(AnimalEntity::getSpecies)).map(entityMapper::mapTo).toList();
    }

    @Override
    public AnimalRecordDto getById(Long id) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(RuntimeException::new);
        return entityMapper.mapTo(animalEntity);
    }

    @Override
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }
    @Override
    public List<AnimalEntity> findAllWithJPQL() {
        return animalRepository.findAllwithJPQL();
    }
    @Override
    public AnimalEntity findAnimalById(Long id) {
        return animalRepository.findAnimalByIdJPQL(id);
    }

}
