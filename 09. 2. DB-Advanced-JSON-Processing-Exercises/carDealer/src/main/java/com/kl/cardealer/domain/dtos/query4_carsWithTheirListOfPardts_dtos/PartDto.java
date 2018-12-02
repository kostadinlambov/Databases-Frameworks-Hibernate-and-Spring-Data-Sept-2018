package com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartDto {
    @Expose
    private String Name;

    @Expose
    private BigDecimal Price;

    public PartDto() {
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return this.Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
