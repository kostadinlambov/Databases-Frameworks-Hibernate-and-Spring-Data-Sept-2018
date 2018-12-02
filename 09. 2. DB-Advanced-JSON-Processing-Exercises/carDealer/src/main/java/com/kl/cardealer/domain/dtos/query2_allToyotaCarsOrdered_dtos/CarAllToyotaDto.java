package com.kl.cardealer.domain.dtos.query2_allToyotaCarsOrdered_dtos;

import com.google.gson.annotations.Expose;

public class CarAllToyotaDto {
    @Expose
    private Integer Id;

    @Expose
    private String Make;

    @Expose
    private String Model;

    @Expose
    private Long TravelledDistance;

    public CarAllToyotaDto() {
    }

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer id) {
        Id = id;
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
