package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query2_dtos.CarExportDtoQuery2;
import com.kl.cardealer.domain.dtos.query2_dtos.CarExportRootDtoQuery2;
import com.kl.cardealer.domain.dtos.seed_dtos.CarImportDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CarImportRootDto;
import com.kl.cardealer.domain.dtos.query4_dtos.CarExportDto;
import com.kl.cardealer.domain.dtos.query4_dtos.CarExportRootDto;
import com.kl.cardealer.domain.dtos.query4_dtos.PartExportDto;
import com.kl.cardealer.domain.dtos.query4_dtos.PartExportRootDto;
import com.kl.cardealer.domain.entities.Car;
import com.kl.cardealer.domain.entities.Part;
import com.kl.cardealer.repositories.CarRepository;
import com.kl.cardealer.repositories.PartRepository;
import com.kl.cardealer.repositories.SupplierRepository;
import com.kl.cardealer.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final CarRepository carRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CarServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, CarRepository carRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.carRepository = carRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public CarExportRootDtoQuery2 getToyotaCarsXml() {
        List<Car> toyotaCars = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        List<CarExportDtoQuery2> carExportDtoQuery2s = toyotaCars
                .stream()
                .map(car -> this.modelMapper
                        .map(car, CarExportDtoQuery2.class))
                .collect(Collectors.toList());
        CarExportRootDtoQuery2 carExportRootDtoQuery2 = new CarExportRootDtoQuery2();
        carExportRootDtoQuery2.setCarExportDtoQuery2List(carExportDtoQuery2s);

        return carExportRootDtoQuery2;
    }

    @Override
    public CarExportRootDto exportCars() {
        List<Car> allCars = this.carRepository.findAll();

        List<CarExportDto> carExportDtoList  = allCars.stream()
                .map(car -> {
                    List<PartExportDto> partDtoList = car.getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartExportDto.class))
                            .collect(Collectors.toList());

                    PartExportRootDto partExportRootDto = new PartExportRootDto();
                    partExportRootDto.setPartExportDtoList(partDtoList);

                    CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);

                    carExportDto.setPartExportRootDto(partExportRootDto);
                    return carExportDto;
                }).collect(Collectors.toList());

        CarExportRootDto carExportRootDto = new CarExportRootDto();
        carExportRootDto.setCarExportDtoList(carExportDtoList);

        return carExportRootDto;
    }

    @Override
    public void seedCarsXml(CarImportRootDto carImportRootDto) {
        for (CarImportDto carSeedDto : carImportRootDto.getCarImportDtoList()) {
            if(!this.validatorUtil.isValid(carSeedDto)){
                this.validatorUtil.violations(carSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            Car car = this.modelMapper.map(carSeedDto, Car.class);

            List<Part> randomParts = this.getRandomParts();
            car.setParts(randomParts);

            this.carRepository.saveAndFlush(car);
        }
    }

    private List<Part> getRandomParts() {

        Random random = new Random();

        List<Part> parts = new ArrayList<>();

        int partsCount = random.nextInt(20-10) + 10;

        for (int i = 0; i < partsCount; i++) {
           parts.add(this.partRepository
                   .getOne(random.nextInt((int)this.partRepository.count()-1) + 1));
        }
        return parts;
    }
}
