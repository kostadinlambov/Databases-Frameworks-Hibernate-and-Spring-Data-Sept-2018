package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query2_allToyotaCarsOrdered_dtos.CarAllToyotaDto;
import com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos.CarWithPartsDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CarSeedDto;

import java.util.List;

public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);

    List<CarAllToyotaDto> getToyotaCars();

    List<CarWithPartsDto> getCarsWithTheirParts();
}
