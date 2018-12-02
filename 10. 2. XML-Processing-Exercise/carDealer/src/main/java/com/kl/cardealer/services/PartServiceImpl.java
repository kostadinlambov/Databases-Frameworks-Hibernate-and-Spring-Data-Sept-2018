package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.seed_dtos.PartImportDto;
import com.kl.cardealer.domain.dtos.seed_dtos.PartImportRootDto;
import com.kl.cardealer.domain.entities.Part;
import com.kl.cardealer.domain.entities.Supplier;
import com.kl.cardealer.repositories.PartRepository;
import com.kl.cardealer.repositories.SupplierRepository;
import com.kl.cardealer.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedPartsXml(PartImportRootDto partImportRootDto) {
        for (PartImportDto partImportDto : partImportRootDto.getPartImportDtos() ) {
            if(!this.validatorUtil.isValid(partImportDto)){
                this.validatorUtil.violations(partImportDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            Part part = this.modelMapper.map(partImportDto, Part.class);

            Supplier randomSupplier = this.getRandomSupplier();
            part.setSupplier(randomSupplier);

            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() {
        Random random = new Random();
        return this.supplierRepository
                .getOne(random.nextInt((int) this.supplierRepository.count() - 1) + 1);
    }
}
