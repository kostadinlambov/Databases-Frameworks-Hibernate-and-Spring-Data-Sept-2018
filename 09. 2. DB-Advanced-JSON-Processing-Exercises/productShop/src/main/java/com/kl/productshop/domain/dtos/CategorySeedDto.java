package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategorySeedDto {
    @Expose
    private String name;

    public CategorySeedDto() {
    }

    @NotNull(message = "Name cannot be null!")
    @Size(min = 3,max = 15, message = "Name must be between 3 and 15 symbols long")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
