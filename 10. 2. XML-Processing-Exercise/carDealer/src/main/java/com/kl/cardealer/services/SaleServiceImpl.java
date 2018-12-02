package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query6_dtos.SaleCarExportDto;
import com.kl.cardealer.domain.dtos.query6_dtos.SaleExportDtoQuery6;
import com.kl.cardealer.domain.dtos.query6_dtos.SaleExportRootDtoQuery6;
import com.kl.cardealer.domain.entities.Car;
import com.kl.cardealer.domain.entities.Customer;
import com.kl.cardealer.domain.entities.Sale;
import com.kl.cardealer.repositories.*;
import com.kl.cardealer.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final PartRepository partRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(PartRepository partRepository, CustomerRepository customerRepository, SupplierRepository supplierRepository, SaleRepository saleRepository, CarRepository carRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public SaleExportRootDtoQuery6 getSalesWithDiscountXml() {
        List<Sale> saleList = this.saleRepository.findAll();

        List<SaleExportDtoQuery6> dtoQuery6s = saleList.stream().map(sale -> {

            SaleExportDtoQuery6 saleExportDtoQuery6 = new SaleExportDtoQuery6();
            SaleCarExportDto saleCarExportDto = new SaleCarExportDto();

            saleCarExportDto.setMake(sale.getCar().getMake());
            saleCarExportDto.setModel(sale.getCar().getModel());
            saleCarExportDto.setTravelledDistance(sale.getCar().getTravelledDistance());

            BigDecimal carPrice = getCarPrice(sale);
            BigDecimal carPriceWithDiscount = carPrice.subtract(carPrice.multiply(new BigDecimal(sale.getDiscount())));

            saleExportDtoQuery6.setSaleCarExportDto(saleCarExportDto);
            saleExportDtoQuery6.setCustomerName(sale.getCustomer().getName());
            saleExportDtoQuery6.setDiscount(sale.getDiscount());
            saleExportDtoQuery6.setPrice(carPrice);
            saleExportDtoQuery6.setPriceWithDiscount(new BigDecimal(String.format("%.2f", carPriceWithDiscount)));

            return saleExportDtoQuery6;
        }).collect(Collectors.toList());

        SaleExportRootDtoQuery6 saleExportRootDtoQuery6 = new SaleExportRootDtoQuery6();
        saleExportRootDtoQuery6.setSaleExportDtoQuery6List(dtoQuery6s);

        return saleExportRootDtoQuery6;
    }

    private BigDecimal getCarPrice(Sale sale) {
        final BigDecimal[] price = {new BigDecimal(0)};

        sale.getCar().getParts().forEach(part ->
                price[0] = price[0].add(part.getPrice())
        );

        return price[0];
    }

    @Override
    public void seedSales() {
        long customersCount = this.customerRepository.count();

        for (int i = 0; i < customersCount; i++) {
            Sale sale = new Sale();

            Car randomCar = this.getRandomCar();
            Customer randomCustomer = this.getRandomCustomer();
            Double discount = this.getRandomDiscount();

            if (randomCustomer.getYoungDriver()) {
                discount += 0.05;
            }

            DecimalFormat df = new DecimalFormat("#.##");
            String discountFormatted = df.format(discount);

            sale.setCar(randomCar);
            sale.setCustomer(randomCustomer);
            sale.setDiscount(Double.parseDouble(discountFormatted));

            this.saleRepository.saveAndFlush(sale);

        }
    }

    private Double getRandomDiscount() {
        Double discount = 0.0;
        Random random = new Random();
        int randomNumber = random.nextInt(8 - 1) + 1;

        switch (randomNumber) {
            case 1:
                discount = 0.0;
                break;
            case 2:
                discount = 0.05;
                break;
            case 3:
                discount = 0.10;
                break;
            case 4:
                discount = 0.15;
                break;
            case 5:
                discount = 0.20;
                break;
            case 6:
                discount = 0.30;
                break;
            case 7:
                discount = 0.40;
                break;
            case 8:
                discount = 0.50;
                break;
        }

        return discount;
    }

    private Customer getRandomCustomer() {
        Random random = new Random();
        return this.customerRepository
                .getOne(random.nextInt((int) this.customerRepository.count() - 1) + 1);
    }

    private Car getRandomCar() {
        Random random = new Random();
        return this.carRepository
                .getOne(random.nextInt((int) this.carRepository.count() - 1) + 1);
    }
}
