package com.accenture.AnimalShelterJPA.service;

import com.accenture.AnimalShelterJPA.data.mapper.AnimalDtoToEntityMapper;
import com.accenture.AnimalShelterJPA.data.mapper.ShelterDtoToEntityMapper;
import com.accenture.AnimalShelterJPA.data.mapper.ShelterEntityToDtoMapper;
import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import com.accenture.AnimalShelterJPA.data.model.ShelterEntity;
import com.accenture.AnimalShelterJPA.data.model.ShelterRecordDto;
import com.accenture.AnimalShelterJPA.data.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {
    public final ShelterRepository shelterRepository;
    public final ShelterDtoToEntityMapper shelterDtoToEntityMapper;
    public final ShelterEntityToDtoMapper shelterEntityToDtoMapper;
    public final AnimalDtoToEntityMapper animalDtoToEntityMapper;
    public final AnimalServiceImpl animalServiceImpl;
    @Autowired
    public ShelterServiceImpl(ShelterRepository shelterRepository, ShelterDtoToEntityMapper shelterDtoToEntityMapper, ShelterEntityToDtoMapper shelterEntityToDtoMapper, AnimalDtoToEntityMapper animalDtoToEntityMapper, AnimalServiceImpl animalServiceImpl) {
        this.shelterRepository = shelterRepository;
        this.shelterDtoToEntityMapper = shelterDtoToEntityMapper;
        this.shelterEntityToDtoMapper = shelterEntityToDtoMapper;
        this.animalDtoToEntityMapper = animalDtoToEntityMapper;
        this.animalServiceImpl = animalServiceImpl;
    }

    @Override
    public void create(ShelterRecordDto shelterRecordDto) {
        //TODO: capacity logic has to be tested
        if (shelterRecordDto.animal_ids().size() <= shelterRecordDto.capacity()) {
            ShelterEntity shelterEntity = shelterDtoToEntityMapper.mapTo(shelterRecordDto);
            shelterRepository.save(shelterEntity);
        } else {
            System.out.println("number of animals surpasses the capacity of the shelter");
            //throw new Exception("number of animals surpasses the capacity of the shelter");
        }
    }

    @Override
    public ShelterRecordDto updateById(Long id, ShelterRecordDto shelterRecordDto) {
        ShelterEntity shelterEntity = shelterDtoToEntityMapper.mapTo(shelterRecordDto);
        shelterEntity.setId(id);
        return shelterEntityToDtoMapper.mapTo(shelterRepository.save(shelterEntity));
    }

    @Override
    public List<ShelterRecordDto> findAll() {
        List<ShelterEntity> shelters = shelterRepository.findAll();
        return shelters.stream().sorted(Comparator.comparing(ShelterEntity::getName)).map(shelterEntityToDtoMapper::mapTo).toList();
    }

    @Override
    public ShelterRecordDto getById(Long id) {
        ShelterEntity shelterEntity = shelterRepository.findById(id).orElseThrow(RuntimeException::new);
        return shelterEntityToDtoMapper.mapTo(shelterEntity);
    }

    @Override
    public void deleteById(Long id) {
        shelterRepository.deleteById(id);
    }
    @Override
    public void addAnimals(Long id, List<AnimalRecordDto> animals) {
        //TODO: has to be tested!!!
        List<AnimalEntity> animalEntities = shelterRepository.findById(id).orElseThrow(RuntimeException::new).getAnimals();
        animals.forEach((a) -> animalEntities.add(animalDtoToEntityMapper.mapTo(a)));
        animals.forEach(animalServiceImpl::create);
    }
}
