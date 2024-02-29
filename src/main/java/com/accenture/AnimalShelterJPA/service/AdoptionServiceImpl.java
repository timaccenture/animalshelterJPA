package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.mapper.AdoptionDtoToEntityMapper;
import com.accenture.AnimalShelterJPA.data.mapper.AdoptionEntityToDtoMapper;
import com.accenture.AnimalShelterJPA.data.model.AdoptionEntity;
import com.accenture.AnimalShelterJPA.data.model.AdoptionRecordDto;
import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.repository.AdoptionRepository;
import com.accenture.AnimalShelterJPA.data.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptionServiceImpl implements AdoptionService{

    public final AdoptionRepository adoptionRepository;
    public final AnimalRepository animalRepository;
    public final AdoptionDtoToEntityMapper adoptionDtoToEntityMapper;
    public final AdoptionEntityToDtoMapper adoptionEntityToDtoMapper;

    @Autowired
    public AdoptionServiceImpl(AdoptionRepository adoptionRepository, AnimalRepository animalRepository, AdoptionDtoToEntityMapper adoptionDtoToEntityMapper, AdoptionEntityToDtoMapper adoptionEntityToDtoMapper) {
        this.adoptionRepository = adoptionRepository;
        this.animalRepository = animalRepository;
        this.adoptionDtoToEntityMapper = adoptionDtoToEntityMapper;
        this.adoptionEntityToDtoMapper = adoptionEntityToDtoMapper;
    }

    @Override
    public void create(AdoptionRecordDto adoptionRecordDto) {
        AdoptionEntity adoptionEntity = adoptionDtoToEntityMapper.mapTo(adoptionRecordDto);
        // when adopter is added, the adopted animal status changes to true
        AnimalEntity animalEntity = adoptionEntity.getAdoptedAnimal();
        animalEntity.setAdopted(true);
        animalRepository.save(animalEntity);

        adoptionRepository.save(adoptionEntity);
    }

    @Override
    public AdoptionRecordDto updateById(Long id, AdoptionRecordDto adoptionRecordDto) {
        //TODO: check if id of the adopted animal got changed, if so change the adoption status
        // of the corresp animals accordinly
        AdoptionEntity adoptionEntity = adoptionDtoToEntityMapper.mapTo(adoptionRecordDto);
        adoptionEntity.setId(id);
        return adoptionEntityToDtoMapper.mapTo(adoptionRepository.save(adoptionEntity));
    }

    @Override
    public List<AdoptionRecordDto> findAll() {
        List<AdoptionEntity> adoptions = adoptionRepository.findAll();
        return adoptions.stream().sorted(Comparator.comparing(AdoptionEntity::getAdopterName)).map(adoptionEntityToDtoMapper::mapTo).toList();
    }

    @Override
    public AdoptionRecordDto getById(Long id) {
        AdoptionEntity adoptionEntity = adoptionRepository.findById(id).orElseThrow(RuntimeException::new);
        return adoptionEntityToDtoMapper.mapTo(adoptionEntity);
    }

    @Override
    public void deleteById(Long id) {
        // when adopter is deleted, the adopted animal status changes to false
        AdoptionEntity adoptionEntity = adoptionRepository.findById(id).orElseThrow(RuntimeException::new);;
        Long adoptedAnimalId = adoptionEntity.getAdoptedAnimal().getId();
        AnimalEntity animalEntity = animalRepository.findById(adoptedAnimalId).orElseThrow(RuntimeException::new);
        animalEntity.setAdopted(false);
        animalRepository.save(animalEntity);

        adoptionRepository.deleteById(id);
    }
}
