package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query2_allToyotaCarsOrdered_dtos.CarAllToyotaDto;
import com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos.CarDto;
import com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos.CarWithPartsDto;
import com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos.PartDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CarSeedDto;
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
    public List<CarAllToyotaDto> getToyotaCars() {
        List<Car> toyotaCars = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        List<CarAllToyotaDto> carAllToyotaDtos = toyotaCars
                .stream()
                .map(car -> this.modelMapper
                        .map(car, CarAllToyotaDto.class))
                .collect(Collectors.toList());

        return carAllToyotaDtos;
    }

    @Override
    public List<CarWithPartsDto> getCarsWithTheirParts() {
        List<Car> allCars = this.carRepository.findAll();

        List<CarWithPartsDto> allCarsDtos = allCars.stream()
                .map(car ->{
                    List<PartDto> partDtoList = car.getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartDto.class))
                            .collect(Collectors.toList());

                    CarDto carDto = this.modelMapper.map(car, CarDto.class);

                    CarWithPartsDto carWithPartsDto = new CarWithPartsDto();
                    carWithPartsDto.setCar(carDto);
                    carWithPartsDto.setParts(partDtoList);

                    return carWithPartsDto;
                }).collect(Collectors.toList());


        return allCarsDtos;
    }

    @Override
    public void seedCars(CarSeedDto[] carSeedDtos) {
        for (CarSeedDto carSeedDto : carSeedDtos) {
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
