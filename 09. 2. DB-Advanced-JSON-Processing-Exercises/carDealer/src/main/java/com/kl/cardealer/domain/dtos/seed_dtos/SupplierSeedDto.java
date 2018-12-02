package com.kl.cardealer.domain.dtos.seed_dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class SupplierSeedDto {

    @Expose
    private String name;

    @Expose
    private Boolean isImporter;

    public SupplierSeedDto() {
    }

    @NotNull(message = "Name cannot be null!")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "IsImporter cannot be null!")
    public Boolean getImporter() {
        return this.isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
