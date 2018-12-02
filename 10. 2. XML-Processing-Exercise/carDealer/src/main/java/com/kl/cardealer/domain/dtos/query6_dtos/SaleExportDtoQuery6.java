package com.kl.cardealer.domain.dtos.query6_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportDtoQuery6 {

    @XmlElement(name = "car")
    private SaleCarExportDto saleCarExportDto;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private Double Discount;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SaleExportDtoQuery6() {
    }

    public SaleCarExportDto getSaleCarExportDto() {
        return this.saleCarExportDto;
    }

    public void setSaleCarExportDto(SaleCarExportDto saleCarExportDto) {
        this.saleCarExportDto = saleCarExportDto;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return this.Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return this.priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
