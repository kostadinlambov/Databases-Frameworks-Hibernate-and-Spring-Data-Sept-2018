package com.kl.cardealer.domain.dtos.query5_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportRootDtoQuery5 {

    @XmlElement(name = "customer")
    private List<CustomerExportDtoQuery5> customerExportDtoQuery5List;

    public CustomerExportRootDtoQuery5() {
    }

    public List<CustomerExportDtoQuery5> getCustomerExportDtoQuery5List() {
        return this.customerExportDtoQuery5List;
    }

    public void setCustomerExportDtoQuery5List(List<CustomerExportDtoQuery5> customerExportDtoQuery5List) {
        this.customerExportDtoQuery5List = customerExportDtoQuery5List;
    }
}
