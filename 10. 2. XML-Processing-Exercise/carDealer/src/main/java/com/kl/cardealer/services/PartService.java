package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.seed_dtos.PartImportRootDto;

public interface PartService {

    void seedPartsXml(PartImportRootDto partImportRootDto);
}
