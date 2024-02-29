package com.accenture.AnimalShelterJPA.data.model;

import java.time.LocalDate;

public record AdoptionRecordDto (Long id, String adopterName, LocalDate adoptionDate, Long adoptedAnimalId) {
}
