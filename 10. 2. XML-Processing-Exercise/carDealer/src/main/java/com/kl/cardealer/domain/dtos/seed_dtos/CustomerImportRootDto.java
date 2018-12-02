package com.kl.cardealer.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportRootDto {

    @XmlElement(name = "customer")
    List<CustomerImportDto> customerImportDtoList;

    public CustomerImportRootDto() {
    }

    public List<CustomerImportDto> getCustomerImportDtoList() {
        return this.customerImportDtoList;
    }

    public void setCustomerImportDtoList(List<CustomerImportDto> customerImportDtoList) {
        this.customerImportDtoList = customerImportDtoList;
    }
}
