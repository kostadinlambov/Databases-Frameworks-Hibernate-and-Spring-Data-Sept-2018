package com.kl.cardealer.domain.dtos.seed_dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CustomerSeedDtos {
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private Boolean isYoungDriver;

    public CustomerSeedDtos() {
    }

    @NotNull(message = "Name cannot be null!")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Birth Date cannot be null!")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @NotNull(message = "IsYoungDriver cannot be null!")
    public Boolean getYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
