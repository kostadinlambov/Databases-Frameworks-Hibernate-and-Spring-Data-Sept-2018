package com.kl.cardealer.domain.dtos.query4_carsWithTheirListOfPardts_dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CarWithPartsDto {
    @Expose
    private CarDto  car;

    @Expose
    private List<PartDto> parts;

    public CarWithPartsDto() {
        this.parts = new ArrayList<>();
    }

    public CarDto getCar() {
        return this.car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<PartDto> getParts() {
        return this.parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
