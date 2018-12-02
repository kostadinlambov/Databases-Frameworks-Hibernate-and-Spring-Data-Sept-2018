package com.kl.productshop.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportRootDto {

    @XmlElement(name = "product")
    private List<ProductImportDto> productImportDtoList;

    public ProductImportRootDto() {
    }

    public List<ProductImportDto> getProductImportDtoList() {
        return this.productImportDtoList;
    }

    public void setProductImportDtoList(List<ProductImportDto> productImportDtoList) {
        this.productImportDtoList = productImportDtoList;
    }
}
