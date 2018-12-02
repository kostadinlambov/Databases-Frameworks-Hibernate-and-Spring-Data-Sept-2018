package com.kl.cardealer.domain.dtos.query6_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportRootDtoQuery6 {

    @XmlElement(name = "sale")
    private List<SaleExportDtoQuery6> saleExportDtoQuery6List;

    public SaleExportRootDtoQuery6() {
    }

    public List<SaleExportDtoQuery6> getSaleExportDtoQuery6List() {
        return this.saleExportDtoQuery6List;
    }

    public void setSaleExportDtoQuery6List(List<SaleExportDtoQuery6> saleExportDtoQuery6List) {
        this.saleExportDtoQuery6List = saleExportDtoQuery6List;
    }
}
