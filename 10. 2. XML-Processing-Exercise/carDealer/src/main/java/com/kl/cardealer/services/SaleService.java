package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query6_dtos.SaleExportRootDtoQuery6;

public interface SaleService {
    void seedSales();

    SaleExportRootDtoQuery6 getSalesWithDiscountXml();
}
