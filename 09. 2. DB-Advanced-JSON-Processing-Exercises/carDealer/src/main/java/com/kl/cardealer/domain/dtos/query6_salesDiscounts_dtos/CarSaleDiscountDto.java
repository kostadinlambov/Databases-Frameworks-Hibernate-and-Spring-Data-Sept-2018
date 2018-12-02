package com.kl.cardealer.domain.dtos.query6_salesDiscounts_dtos;

import com.google.gson.annotations.Expose;

public class CarSaleDiscountDto {
    @Expose
    private String Make;

    @Expose
    private String Model;

    @Expose
    private Long TravelledDistance;

    public CarSaleDiscountDto() {
    }

    public String getMake() {
        return this.Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return this.Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Long getTravelledDistance() {
        return this.TravelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
