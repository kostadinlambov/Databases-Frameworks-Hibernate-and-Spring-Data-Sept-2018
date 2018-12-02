package com.kl.productshop.domain.dtos.query1_dtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductExportRootDto {


    @XmlElement(name = "product")
    private List<ProductExportDto> productExportDtoList;

    public ProductExportRootDto() {
    }

    public List<ProductExportDto> getProductExportDtoList() {
        return this.productExportDtoList;
    }

    public void setProductExportDtoList(List<ProductExportDto> productExportDtoList) {
        this.productExportDtoList = productExportDtoList;
    }
}
