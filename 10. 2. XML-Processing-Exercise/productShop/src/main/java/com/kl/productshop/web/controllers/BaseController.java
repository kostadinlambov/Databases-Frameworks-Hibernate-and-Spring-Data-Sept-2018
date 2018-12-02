package com.kl.productshop.web.controllers;

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
        // User initialisation
        System.out.println(this.importController.importUsers());
        // Category initialisation
        System.out.println(this.importController.importCategories());
        // Product initialisation
        System.out.println(this.importController.importProducts());

        /* Query 1 - Products In Range*/
        this.exportController.getOrderedCustomers();

    }
}
