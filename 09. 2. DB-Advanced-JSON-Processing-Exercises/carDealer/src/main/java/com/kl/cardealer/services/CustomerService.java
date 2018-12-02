package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query5_totalSalesByCustomer.CustomerTotalSalesDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerSeedDtos;
import com.kl.cardealer.domain.dtos.query1_orderedCustomers_dtos.CustomersGetOrderedDto;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomerSeedDtos[] customerSeedDtos);

    List<CustomersGetOrderedDto> getAllCustomers();

    List<CustomerTotalSalesDto> getTotalSalesByCustomers();
}
