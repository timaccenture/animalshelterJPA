package com.accenture.AnimalShelterJPA.data.mapper;

public interface Mapper <SOURCE,TARGET>{
    TARGET mapTo(SOURCE source);
}
