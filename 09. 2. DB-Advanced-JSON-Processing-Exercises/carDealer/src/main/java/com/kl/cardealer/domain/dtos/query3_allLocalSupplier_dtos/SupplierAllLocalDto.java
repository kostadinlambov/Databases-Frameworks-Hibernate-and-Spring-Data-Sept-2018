package com.kl.cardealer.domain.dtos.query3_allLocalSupplier_dtos;

import com.google.gson.annotations.Expose;
import com.kl.cardealer.domain.entities.Part;

import java.util.List;

public class SupplierAllLocalDto {
    @Expose
    private Integer Id;
    @Expose
    private String Name;
    @Expose
    private Integer partsCount;

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
