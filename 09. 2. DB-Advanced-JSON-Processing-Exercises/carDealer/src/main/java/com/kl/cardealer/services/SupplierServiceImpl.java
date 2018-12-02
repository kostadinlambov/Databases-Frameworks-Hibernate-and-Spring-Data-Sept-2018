package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.query3_allLocalSupplier_dtos.SupplierAllLocalDto;
import com.kl.cardealer.domain.dtos.seed_dtos.SupplierSeedDto;
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
    public List<SupplierAllLocalDto> getLocalSuppliers() {
        List<Supplier> localSuppliers = this.supplierRepository.getAllByIsImporterFalse();

        return localSuppliers
                .stream()
                .map(supplier -> {
                    SupplierAllLocalDto currentSupplier = this.modelMapper.map(supplier, SupplierAllLocalDto.class);
                    currentSupplier.setPartsCount(supplier.getParts().size());
                    return currentSupplier;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void seedSuppliers(SupplierSeedDto[] supplierSeedDtos) {
        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            if(!this.validatorUtil.isValid(supplierSeedDto)){
                this.validatorUtil.violations(supplierSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage())
                        );

                continue;
            }

            Supplier entity = this.modelMapper.map(supplierSeedDto, Supplier.class);
            this.supplierRepository.saveAndFlush(entity);
        }
    }


}
