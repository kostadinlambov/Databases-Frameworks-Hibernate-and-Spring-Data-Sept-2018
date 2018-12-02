package com.kl.cardealer.util.entitiesSeed;

import com.google.gson.Gson;
import com.kl.cardealer.domain.dtos.seed_dtos.CarSeedDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerSeedDtos;
import com.kl.cardealer.domain.dtos.seed_dtos.PartSeedDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierSeedDto;
import com.kl.cardealer.io.FileIOUtil;
import com.kl.cardealer.repositories.*;
import com.kl.cardealer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class EntitiesSeedExecutor {
    private final static String SUPPLIERS_FILE_PATH = "files/suppliers.json";
    private final static String PARTS_FILE_PATH = "files/parts.json";
    private final static String CUSTOMERS_FILE_PATH = "files/customers.json";
    private final static String CARS_FILE_PATH = "files/cars.json";

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    private final Gson gson;
    private final FileIOUtil fileIOUtil;


    @Autowired
    public EntitiesSeedExecutor(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, Gson gson, FileIOUtil fileIOUtil){
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
    }

    @PostConstruct
    public void insertEntities() throws IOException {

        // Supplier initialisation
        if (this.supplierRepository.count() == 0L) {
            String suppliersFileContent = this.fileIOUtil.readJson(SUPPLIERS_FILE_PATH);
            SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(suppliersFileContent, SupplierSeedDto[].class);
            this.supplierService.seedSuppliers(supplierSeedDtos);
        }

        // Part initialisation
        if (this.partRepository.count() == 0L) {
            String partsFileContent = this.fileIOUtil.readJson(PARTS_FILE_PATH);
            PartSeedDto[] partSeedDtos = this.gson.fromJson(partsFileContent, PartSeedDto[].class);
            this.partService.seedParts(partSeedDtos);
        }

        // Cars initialisation
        if (this.carRepository.count() == 0L) {
            String carsFileContent = this.fileIOUtil.readJson(CARS_FILE_PATH);
            CarSeedDto[] carSeedDtos = this.gson.fromJson(carsFileContent, CarSeedDto[].class);
            this.carService.seedCars(carSeedDtos);
        }

        // Customer initialisation
        if (this.customerRepository.count() == 0L) {
            String customerFileContent = this.fileIOUtil.readJson(CUSTOMERS_FILE_PATH);
            CustomerSeedDtos[] customerSeedDtos = this.gson.fromJson(customerFileContent, CustomerSeedDtos[].class);
            this.customerService.seedCustomers(customerSeedDtos);
        }

        // Sales initialisation
        if (this.saleRepository.count() == 0L) {
            this.saleService.seedSales();
        }
    }
}
