package com.kl.cardealer.domain.dtos.query1_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportRootDto {

    @XmlElement(name = "customer")
    private List<CustomerExportDto> customerExportDtoList;

    public CustomerExportRootDto() {
    }

    public List<CustomerExportDto> getCustomerExportDtoList() {
        return this.customerExportDtoList;
    }

    public void setCustomerExportDtoList(List<CustomerExportDto> customerExportDtoList) {
        this.customerExportDtoList = customerExportDtoList;
    }
}
