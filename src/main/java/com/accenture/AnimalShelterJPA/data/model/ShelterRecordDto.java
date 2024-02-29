package com.accenture.AnimalShelterJPA.data.model;

import java.util.List;

public record ShelterRecordDto (Long id, String name, String address, Integer capacity, List<Long> animal_ids) {
}
