package com.accenture.AnimalShelterJPA.controller;

import com.accenture.AnimalShelterJPA.data.model.AnimalEntity;
import com.accenture.AnimalShelterJPA.data.model.AnimalRecordDto;
import com.accenture.AnimalShelterJPA.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {
    private final AnimalService animalServiceImpl;
    @Autowired
    public AnimalController(AnimalService animalServiceImpl) {
        this.animalServiceImpl = animalServiceImpl;
    }

    @PostMapping("/api/animal/v1")
    public void createAnimal(@RequestBody AnimalRecordDto animal) {
        animalServiceImpl.create(animal);
    }
    @GetMapping("/api/animal/v1")
    public ResponseEntity<List<AnimalRecordDto>> getAllAnimals() {
        return new ResponseEntity<>(animalServiceImpl.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/animal/v1/{id}")
    public ResponseEntity<AnimalRecordDto> getAnimalById (@PathVariable("id") Long id) {
        return new ResponseEntity<>(animalServiceImpl.getById(id), HttpStatus.OK);
    }
    @PutMapping("/api/animal/v1/{id}")
    public void updateById(@PathVariable("id") Long id, @RequestBody AnimalRecordDto animal){
        animalServiceImpl.updateById(id,animal);
    }
    @DeleteMapping("/api/animal/v1/{id}")
    public void deleteAnimal(@PathVariable("id") Long id) {
        animalServiceImpl.deleteById(id);
    }

    @GetMapping("/api/animal/v1/all")
    public ResponseEntity<List<AnimalEntity>> findall() {
        return new ResponseEntity<>(animalServiceImpl.findAllWithJPQL(), HttpStatus.OK);
    }
    @GetMapping("/api/animal/v2/{id}")
    public ResponseEntity<AnimalEntity> findAnimalById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(animalServiceImpl.findAnimalById(id),HttpStatus.OK);
    }
}
