package com.kl.cardealer.domain.dtos.query1_orderedCustomers_dtos;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class CustomersGetOrderedDto {
    @Expose
    private Integer Id;
    @Expose
    private String Name;
    @Expose
    private Date BirthDate;
    @Expose
    private Boolean IsYoungDriver;
    @Expose
    private List<CustomerSalesDto> Sales;

    public CustomersGetOrderedDto() {
    }

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

    public Date getBirthDate() {
        return this.BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return this.IsYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public List<CustomerSalesDto> getSales() {
        return this.Sales;
    }

    public void setSales(List<CustomerSalesDto> sales) {
        Sales = sales;
    }
}
