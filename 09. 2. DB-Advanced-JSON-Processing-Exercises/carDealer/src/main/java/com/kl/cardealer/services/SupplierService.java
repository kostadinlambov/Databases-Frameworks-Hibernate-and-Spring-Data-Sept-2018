package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query3_allLocalSupplier_dtos.SupplierAllLocalDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierSeedDto;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

    List<SupplierAllLocalDto> getLocalSuppliers();
}

