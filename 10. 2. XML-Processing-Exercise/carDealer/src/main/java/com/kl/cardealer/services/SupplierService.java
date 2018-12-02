package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query3_dtos.SupplierExportRootDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierImportRootDto;


public interface SupplierService {

    void seedSuppliersXml(SupplierImportRootDto SupplierImportRootDto);

    SupplierExportRootDto getLocalSuppliersXml();
}

