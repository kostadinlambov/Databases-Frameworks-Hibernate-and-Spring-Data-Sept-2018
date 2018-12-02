package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query2_dtos.CarExportRootDtoQuery2;
import com.kl.cardealer.domain.dtos.seed_dtos.CarImportRootDto;

import com.kl.cardealer.domain.dtos.query4_dtos.CarExportRootDto;


public interface CarService {
    void seedCarsXml(CarImportRootDto carImportRootDto);

    CarExportRootDto exportCars();

    CarExportRootDtoQuery2 getToyotaCarsXml();


}
