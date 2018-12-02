package com.kl.cardealer.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierImportDto> supplierAllLocalDtos;

    public SupplierImportRootDto() {
        this.supplierAllLocalDtos = new ArrayList<>();
    }

    public List<SupplierImportDto> getSupplierAllLocalDtos() {
        return this.supplierAllLocalDtos;
    }

    public void setSupplierAllLocalDtos(List<SupplierImportDto> supplierAllLocalDtos) {
        this.supplierAllLocalDtos = supplierAllLocalDtos;
    }
}
