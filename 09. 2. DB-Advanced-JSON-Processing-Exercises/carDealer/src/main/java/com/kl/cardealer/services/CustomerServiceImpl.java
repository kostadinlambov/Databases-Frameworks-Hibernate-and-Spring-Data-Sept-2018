package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query5_totalSalesByCustomer.CustomerTotalSalesDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerSeedDtos;
import com.kl.cardealer.domain.dtos.query1_orderedCustomers_dtos.CustomersGetOrderedDto;
import com.kl.cardealer.domain.entities.Customer;
import com.kl.cardealer.repositories.CustomerRepository;
import com.kl.cardealer.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomersGetOrderedDto> getAllCustomers() {
        List<Customer> customers = this.customerRepository.getAllCustomersOrdered();

        List<CustomersGetOrderedDto> customersGetOrderedDtos = customers.stream()
                .map(customer -> this.modelMapper.map(customer, CustomersGetOrderedDto.class))
                .collect(Collectors.toList());

        return customersGetOrderedDtos;
    }

    @Override
    public List<CustomerTotalSalesDto> getTotalSalesByCustomers() {
        List<Customer> customers = this.customerRepository.findAllBySales();

        List<CustomerTotalSalesDto> customerTotalSalesDtos = customers.stream().map(customer -> {

            CustomerTotalSalesDto customerTotalSalesDto = new CustomerTotalSalesDto();
            final BigDecimal[] spentMoney = {new BigDecimal(0)};

            customer.getSales().forEach(sale -> {
                Double discount = sale.getDiscount();
                sale.getCar().getParts().forEach(part -> {

                    BigDecimal price = part.getPrice();
                    price = price.subtract(price.multiply(new BigDecimal(discount)));

                    spentMoney[0] = spentMoney[0].add(price);
                });
            });

            DecimalFormat df = new DecimalFormat("#.##");
            String spentMoneyFormatted = df.format(spentMoney[0]);

            customerTotalSalesDto.setFullName(customer.getName());
            customerTotalSalesDto.setBoughtCars(customer.getSales().size());
            customerTotalSalesDto.setSpentMoney(new BigDecimal(spentMoneyFormatted));

            return customerTotalSalesDto;
        }).collect(Collectors.toList());

        return customerTotalSalesDtos;
    }

    @Override
    public void seedCustomers(CustomerSeedDtos[] customerSeedDtos) {
        for (CustomerSeedDtos customerSeedDto : customerSeedDtos) {
            if (!this.validatorUtil.isValid(customerSeedDto)) {
                this.validatorUtil.violations(customerSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage())
                        );

                continue;
            }

            Customer entity = this.modelMapper.map(customerSeedDto, Customer.class);
            this.customerRepository.saveAndFlush(entity);
        }
    }
}
