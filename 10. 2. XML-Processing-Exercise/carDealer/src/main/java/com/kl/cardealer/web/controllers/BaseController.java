package com.kl.cardealer.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController implements CommandLineRunner {
    private final ImportController importController;
    private final ExportController exportController;

    @Autowired
    protected BaseController(ImportController importController, ExportController exportController) {
        this.importController = importController;
        this.exportController = exportController;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Data seed*/
        this.importController.importSuppliers();
        this.importController.seedParts();
        this.importController.seedCars();
        this.importController.seedCustomers();
        this.importController.seedSales();


        /* Query 1 – Ordered Customers */
//        this.exportController.getOrderedCustomers();

        /* Query 2 – Cars from make Toyota */
//        this.exportController.getAllToyotaCars();

        /* Query 3 – Local Suppliers*/
//        this.exportController.getAllLocalSuppliers();

        /* Query 4 – Cars with Their List of Parts */
//        this.exportController.getCarsWithParts();

        /* Query 5 – Total Sales by Customer*/
//        this.exportController.getSalesByCustomers();

        /* Query 6 – Sales with Applied Discount*/
//        this.exportController.salesWithAppliedDiscount();
    }
}
