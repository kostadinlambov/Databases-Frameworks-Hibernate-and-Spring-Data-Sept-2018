package com.kl.cardealer.web.controllers;

import com.kl.cardealer.domain.dtos.seed_dtos.CarImportRootDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerImportRootDto;
import com.kl.cardealer.domain.dtos.seed_dtos.PartImportRootDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierImportRootDto;
import com.kl.cardealer.io.xmlParser.XmlParser;
import com.kl.cardealer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Controller
public class ImportController {
    private final static String SUPPLIERS_XML_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\suppliers.xml";
    private final static String PARTS_XML_PATH =System.getProperty("user.dir") + "\\src\\main\\resources\\files\\parts.xml";
    private final static String CARS_XML_PATH = System.getProperty("user.dir") +"\\src\\main\\resources\\files\\cars.xml";
    private final static String CUSTOMER_XML_PATH =System.getProperty("user.dir") + "\\src\\main\\resources\\files\\customers.xml";

    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public ImportController(SupplierService supplierService, XmlParser xmlParser, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    public String importSuppliers() throws JAXBException, FileNotFoundException {
        SupplierImportRootDto supplierImportRootDto = this.xmlParser.parseXml(SupplierImportRootDto.class, SUPPLIERS_XML_PATH);
        this.supplierService.seedSuppliersXml(supplierImportRootDto);
        return "Imported Suppliers";
    }

    public String seedParts() throws JAXBException, FileNotFoundException {
        PartImportRootDto partImportRootDto = this.xmlParser.parseXml(PartImportRootDto.class, PARTS_XML_PATH);
        this.partService.seedPartsXml(partImportRootDto);
        return "Imported parts";
    }

    public String seedCars() throws JAXBException, FileNotFoundException {
        CarImportRootDto carImportRootDto = this.xmlParser.parseXml(CarImportRootDto.class, CARS_XML_PATH);
        this.carService.seedCarsXml(carImportRootDto);
        return "Imported Cars";
    }

    public String seedCustomers() throws JAXBException, FileNotFoundException {
        CustomerImportRootDto customerImportRootDto = this.xmlParser.parseXml(CustomerImportRootDto.class, CUSTOMER_XML_PATH);
        this.customerService.seedCustomersXml(customerImportRootDto);
        return "Imported Customers";
    }

    public String seedSales() {
        this.saleService.seedSales();
        return "Imported Sales";
    }
}
