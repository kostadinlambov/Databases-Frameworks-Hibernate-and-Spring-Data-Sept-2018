package com.kl.cardealer.web.controllers;

import com.google.gson.Gson;
import com.kl.cardealer.domain.dtos.query1_orderedCustomers_dtos.CustomersGetOrderedDto;
import com.kl.cardealer.domain.dtos.query2_allToyotaCarsOrdered_dtos.CarAllToyotaDto;
import com.kl.cardealer.domain.dtos.query3_allLocalSupplier_dtos.SupplierAllLocalDto;
import com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos.CarWithPartsDto;
import com.kl.cardealer.domain.dtos.query5_totalSalesByCustomer.CustomerTotalSalesDto;
import com.kl.cardealer.domain.dtos.query6_salesDiscounts_dtos.SaleDiscountDto;
import com.kl.cardealer.io.FileIOUtil;
import com.kl.cardealer.services.CarService;
import com.kl.cardealer.services.CustomerService;
import com.kl.cardealer.services.SaleService;
import com.kl.cardealer.services.SupplierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {
    private final static String ORDERED_CUSTOMERS_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/output/ordered-customers.json";
    private final static String TOYOTA_CARS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/toyota-cars.json";
    private final static String ALL_LOCAL_SUPPLIER_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/local-suppliers.json";
    private final static String CARS_AND_PARTS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/cars-and-parts.json";
    private final static String CUSTOMER_TOTAL_SALES_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/customers-total-sales.json";
    private final static String SALES_DISCOUNTS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/sales-discounts.json";

    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    public CarDealerController(CustomerService customerService, CarService carService, SupplierService supplierService, SaleService saleService, FileIOUtil fileIOUtil, Gson gson) {
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Query 1 – Ordered Customers*/
//            this.getOrderedCustomers();
        /* Query 2 – Cars from make Toyota*/
//            this.getAllToyotaCars();
        /* Query 3 – Local Suppliers*/
//        this.getAllLocalSuppliers();
        /* Query 4 – Cars with Their List of Parts*/
//        this.getCarsWithParts();
        /* Query 5 – Total Sales by Customer*/
//        this.getSalesByCustomers();
        /* Query 6 – Sales with Applied Discount*/
//        this.salesWithAppliedDiscount();
    }

    private void salesWithAppliedDiscount() throws IOException {
        List<SaleDiscountDto> salesWithDiscountDtos = this.saleService.getSalesWithDiscount();

        String salesWithDiscountJSON = this.gson.toJson(salesWithDiscountDtos);

        System.out.println(salesWithDiscountJSON);

        this.fileIOUtil.writeToFile(salesWithDiscountJSON, SALES_DISCOUNTS_FILE_PATH);
    }

    private void getSalesByCustomers() throws IOException {
        List<CustomerTotalSalesDto> totalSalesByCustomersDtos = this.customerService.getTotalSalesByCustomers();

        String totalSalesByCustomersJSON = this.gson.toJson(totalSalesByCustomersDtos);

        System.out.println(totalSalesByCustomersJSON);

        this.fileIOUtil.writeToFile(totalSalesByCustomersJSON, CUSTOMER_TOTAL_SALES_FILE_PATH);
    }

    private void getCarsWithParts() throws IOException {
        List<CarWithPartsDto> carsWithTheirPartsDtos = this.carService.getCarsWithTheirParts();

        String carWithPartsJSON = this.gson.toJson(carsWithTheirPartsDtos);

        System.out.println(carWithPartsJSON);

        this.fileIOUtil.writeToFile(carWithPartsJSON, CARS_AND_PARTS_FILE_PATH);
    }


    private void getAllLocalSuppliers() throws IOException {
        List<SupplierAllLocalDto> localSuppliersDtos = this.supplierService.getLocalSuppliers();

        String supplierAllLocalJSON = this.gson.toJson(localSuppliersDtos);

        System.out.println(supplierAllLocalJSON);

        this.fileIOUtil.writeToFile(supplierAllLocalJSON, ALL_LOCAL_SUPPLIER_FILE_PATH);
    }

    private void getAllToyotaCars() throws IOException {
        List<CarAllToyotaDto> toyotaCarsDtos = this.carService.getToyotaCars();

        String toyotaCarsJSON = this.gson.toJson(toyotaCarsDtos);

        System.out.println(toyotaCarsJSON);

        this.fileIOUtil.writeToFile(toyotaCarsJSON, TOYOTA_CARS_FILE_PATH);
    }

    private void getOrderedCustomers() throws IOException {
        List<CustomersGetOrderedDto> allCustomersDtos = this.customerService.getAllCustomers();

        String allCustomersJSON = this.gson.toJson(allCustomersDtos);

        System.out.println(allCustomersJSON);

       this.fileIOUtil.writeToFile(allCustomersJSON,ORDERED_CUSTOMERS_FILE_PATH);
    }
}
