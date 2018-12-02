package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query1_dtos.CustomerExportRootDto;
import com.kl.cardealer.domain.dtos.query5_dtos.CustomerExportRootDtoQuery5;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerImportRootDto;

public interface CustomerService {
    void seedCustomersXml(CustomerImportRootDto customerImportRootDto);

    CustomerExportRootDto getOrderedCustomers();

    CustomerExportRootDtoQuery5 getTotalSalesByCustomersXml();

}
