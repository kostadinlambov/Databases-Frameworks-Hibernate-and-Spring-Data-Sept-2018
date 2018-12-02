package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query3_dtos.SupplierExportDto;
import com.kl.cardealer.domain.dtos.query3_dtos.SupplierExportRootDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierImportDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierImportRootDto;
import com.kl.cardealer.domain.entities.Supplier;
import com.kl.cardealer.repositories.SupplierRepository;
import com.kl.cardealer.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierExportRootDto getLocalSuppliersXml() {
        List<Supplier> localSuppliers = this.supplierRepository.getAllByIsImporterFalse();

        List<SupplierExportDto> supplierExportDtos = localSuppliers
                .stream()
                .map(supplier -> {
                    SupplierExportDto currentSupplier = this.modelMapper.map(supplier, SupplierExportDto.class);
                    currentSupplier.setPartCount(supplier.getParts().size());
                    return currentSupplier;
                })
                .collect(Collectors.toList());

        SupplierExportRootDto supplierExportRootDto = new SupplierExportRootDto();
        supplierExportRootDto.setSupplierExportDtos(supplierExportDtos);

        return supplierExportRootDto;
    }

    @Override
    public void seedSuppliersXml(SupplierImportRootDto SupplierImportRootDto) {
        for (SupplierImportDto supplierImportDto : SupplierImportRootDto.getSupplierAllLocalDtos()) {
            if (!this.validatorUtil.isValid(supplierImportDto)) {
                this.validatorUtil.violations(supplierImportDto)
                        .forEach(violation -> System.out.println(violation.getMessage())
                        );

                continue;
            }

            Supplier entity = this.modelMapper.map(supplierImportDto, Supplier.class);
            this.supplierRepository.saveAndFlush(entity);
        }
    }
}
