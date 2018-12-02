package com.kl.cardealer.services;

import com.kl.cardealer.domain.dtos.seed_dtos.PartSeedDto;

public interface PartService {
    void seedParts(PartSeedDto[] partSeedDtos);
}
