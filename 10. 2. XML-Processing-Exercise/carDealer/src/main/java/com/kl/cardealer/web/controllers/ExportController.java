package com.kl.cardealer.web.controllers;

import com.kl.cardealer.domain.dtos.query2_dtos.CarExportRootDtoQuery2;
import com.kl.cardealer.domain.dtos.query1_dtos.CustomerExportRootDto;
import com.kl.cardealer.domain.dtos.query3_dtos.SupplierExportRootDto;
import com.kl.cardealer.domain.dtos.query4_dtos.CarExportRootDto;
import com.kl.cardealer.domain.dtos.query5_dtos.CustomerExportRootDtoQuery5;
import com.kl.cardealer.domain.dtos.query6_dtos.SaleExportRootDtoQuery6;
import com.kl.cardealer.io.xmlParser.XmlParser;
import com.kl.cardealer.services.CarService;
import com.kl.cardealer.services.CustomerService;
import com.kl.cardealer.services.SaleService;
import com.kl.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Controller
public class ExportController {

        private final static String ORDERED_CUSTOMERS_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/output/cars-and-parts.xml";
    private final static String TOYOTA_CARS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/toyota-cars.xml";
    private final static String ALL_LOCAL_SUPPLIER_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/local-suppliers.xml";
    private final static String CARS_AND_PARTS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/ordered-customers.xml";
    private final static String CUSTOMER_TOTAL_SALES_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/customers-total-sales.xml";
    private final static String SALES_DISCOUNTS_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/output/sales-discounts.xml";



    private final CarService carService;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final XmlParser xmlParser;

    @Autowired
    public ExportController(CarService carService, CustomerService customerService, SupplierService supplierService, SaleService saleService, XmlParser xmlParser) {
        this.carService = carService;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.xmlParser = xmlParser;
    }

    public String getCarsWithParts() throws FileNotFoundException, JAXBException {
        CarExportRootDto carExportRootDto = this.carService.exportCars();
        this.xmlParser.exportXml(carExportRootDto, CarExportRootDto.class, CARS_AND_PARTS_FILE_PATH);
        return null;
    }

    public String getOrderedCustomers() throws FileNotFoundException, JAXBException {
        CustomerExportRootDto orderedCustomers = this.customerService.getOrderedCustomers();
        this.xmlParser.exportXml(orderedCustomers, CustomerExportRootDto.class, ORDERED_CUSTOMERS_FILE_PATH);
        return null;
    }

    public String getAllToyotaCars() throws FileNotFoundException, JAXBException {
        CarExportRootDtoQuery2 carExportRootDtoQuery2 = this.carService.getToyotaCarsXml();
        this.xmlParser.exportXml(carExportRootDtoQuery2, CarExportRootDtoQuery2.class, TOYOTA_CARS_FILE_PATH);
        return null;

    }

    public String getAllLocalSuppliers() throws FileNotFoundException, JAXBException {
        SupplierExportRootDto supplierExportRootDto = this.supplierService.getLocalSuppliersXml();
        this.xmlParser.exportXml(supplierExportRootDto, SupplierExportRootDto.class, ALL_LOCAL_SUPPLIER_FILE_PATH);
        return null;

    }

    public String getSalesByCustomers() throws FileNotFoundException, JAXBException {
        CustomerExportRootDtoQuery5 customerExportRootDtQuery5 = this.customerService.getTotalSalesByCustomersXml();
        this.xmlParser.exportXml(customerExportRootDtQuery5, CustomerExportRootDtoQuery5.class, CUSTOMER_TOTAL_SALES_FILE_PATH);
        return null;

    }

    public String salesWithAppliedDiscount() throws FileNotFoundException, JAXBException {
        SaleExportRootDtoQuery6 saleExportRootDtoQuery6 = this.saleService.getSalesWithDiscountXml();
        this.xmlParser.exportXml(saleExportRootDtoQuery6, SaleExportRootDtoQuery6.class, SALES_DISCOUNTS_FILE_PATH);
        return null;
    }
}
