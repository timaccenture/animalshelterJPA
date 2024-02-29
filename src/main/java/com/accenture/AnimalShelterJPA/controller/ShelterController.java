package com.accenture.AnimalShelterJPA.controller;

import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import com.accenture.AnimalShelterJPA.data.model.ShelterRecordDto;
import com.accenture.AnimalShelterJPA.service.ShelterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShelterController {
    private final ShelterServiceImpl shelterServiceImpl;

    public ShelterController(ShelterServiceImpl shelterServiceImpl) {
        this.shelterServiceImpl = shelterServiceImpl;
    }

    @PostMapping("/api/shelter/v1")
    public void createShelter(@RequestBody ShelterRecordDto shelterRecordDto) {
        shelterServiceImpl.create(shelterRecordDto);
    }

    @GetMapping("/api/shelter/v1")
    public ResponseEntity<List<ShelterRecordDto>> getAllShelter() {
        return new ResponseEntity<>(shelterServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/shelter/v1/{id}")
    public  ResponseEntity<ShelterRecordDto> getShelterById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(shelterServiceImpl.getById(id), HttpStatus.OK);
    }
    /*
    @PutMapping("/api/shelter/v1/{id}")
    public void updateById(@PathVariable("id") Long id, @RequestBody ShelterRecordDto shelterRecordDto) {
        shelterServiceImpl.updateById(id, shelterRecordDto);
    }
    */
    @DeleteMapping("/api/shelter/v1/{id}")
    public void deleteShelter(@PathVariable("id") Long id) {
        shelterServiceImpl.deleteById(id);
    }

    @PutMapping("/api/shelter/v1/{id}")
    public void addAnimals(Long id, List<AnimalRecordDto> animalRecordDtos) {
        shelterServiceImpl.addAnimals(id, animalRecordDtos);
    }
}
