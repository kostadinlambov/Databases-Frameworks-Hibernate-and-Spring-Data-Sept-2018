package com.kl.cardealer.domain.dtos.query1_orderedCustomers_dtos;

import com.google.gson.annotations.Expose;


public class CustomerSalesDto {
    @Expose
    private String  CarMake;
    @Expose
    private String CarModel;
    @Expose
    private Double Discount;

    public CustomerSalesDto() {
    }

    public String getCarMake() {
        return this.CarMake;
    }

    public void setCarMake(String carMake) {
        CarMake = carMake;
    }

    public Double getDiscount() {
        return this.Discount;
    }

    public void setDiscount(Double discount) {
        this.Discount = discount;
    }

    public String getCarModel() {
        return this.CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }
}
