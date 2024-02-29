package com.accenture.AnimalShelterJPA.controller;

import com.accenture.AnimalShelterJPA.data.model.AdoptionEntity;
import com.accenture.AnimalShelterJPA.data.model.AdoptionRecordDto;
import com.accenture.AnimalShelterJPA.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdoptionController {
    private final AdoptionService adoptionServiceImpl;
    @Autowired
    public AdoptionController(AdoptionService adoptionServiceImpl) {
        this.adoptionServiceImpl = adoptionServiceImpl;
    }
    @PostMapping("/api/adoption/v1")
    public void createAdoption(@RequestBody AdoptionRecordDto adoptionRecordDto) {
        adoptionServiceImpl.create(adoptionRecordDto);
    }

    @GetMapping("/api/adoption/v1")
    public ResponseEntity<List<AdoptionRecordDto>> getAllAdoptions() {
        return new ResponseEntity<>(adoptionServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/adoption/v1/{id}")
    public ResponseEntity<AdoptionRecordDto> getAdoptionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adoptionServiceImpl.getById(id), HttpStatus.OK);
    }

    @PutMapping("/api/adoption/v1/{id}")
    public void updateById(@PathVariable("id") Long id, @RequestBody AdoptionRecordDto adoptionRecordDto) {
        adoptionServiceImpl.updateById(id, adoptionRecordDto);
    }

    @DeleteMapping("/api/adoption/v1/{id}")
    public void deleteAdoption(@PathVariable("id") Long id) {
        adoptionServiceImpl.deleteById(id);
    }
}
