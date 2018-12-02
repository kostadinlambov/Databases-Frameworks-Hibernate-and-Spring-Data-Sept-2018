package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query6_salesDiscounts_dtos.SaleDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SaleDiscountDto> getSalesWithDiscount();
}
