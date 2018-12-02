package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query1_dtos.CustomerExportDto;
import com.kl.cardealer.domain.dtos.query1_dtos.CustomerExportRootDto;
import com.kl.cardealer.domain.dtos.query5_dtos.CustomerExportDtoQuery5;
import com.kl.cardealer.domain.dtos.query5_dtos.CustomerExportRootDtoQuery5;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerImportDto;
import com.kl.cardealer.domain.dtos.seed_dtos.CustomerImportRootDto;

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
    public CustomerExportRootDto getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.getAllCustomersOrdered();

        CustomerExportRootDto customerExportRootDto = new CustomerExportRootDto();

        List<CustomerExportDto> customerExportDtos = customers.stream()
                .map(customer -> this.modelMapper.map(customer, CustomerExportDto.class))
                .collect(Collectors.toList());

        customerExportRootDto.setCustomerExportDtoList(customerExportDtos);

        return customerExportRootDto;
    }

    @Override
    public CustomerExportRootDtoQuery5 getTotalSalesByCustomersXml() {
        List<Customer> customers = this.customerRepository.findAllBySales();

        List<CustomerExportDtoQuery5> dtoQuery5s = customers.stream().map(customer -> {

            CustomerExportDtoQuery5 customerExportDtoQuery5 = new CustomerExportDtoQuery5();
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

            customerExportDtoQuery5.setFullName(customer.getName());
            customerExportDtoQuery5.setBoughtCars(customer.getSales().size());
            customerExportDtoQuery5.setSpentMoney(new BigDecimal(spentMoneyFormatted));

            return customerExportDtoQuery5;
        }).collect(Collectors.toList());


        CustomerExportRootDtoQuery5 customerExportRootDtoQuery5 = new CustomerExportRootDtoQuery5();
        customerExportRootDtoQuery5.setCustomerExportDtoQuery5List(dtoQuery5s);

        return customerExportRootDtoQuery5;
    }

    @Override
    public void seedCustomersXml(CustomerImportRootDto customerImportRootDto) {
        for (CustomerImportDto customerSeedDto : customerImportRootDto.getCustomerImportDtoList()) {
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
