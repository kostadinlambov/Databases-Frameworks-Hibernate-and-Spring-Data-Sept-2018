package com.kl.cardealer.domain.dtos.query3_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportDto {

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "parts_count")
    private Integer partCount;

    public SupplierExportDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartCount() {
        return this.partCount;
    }

    public void setPartCount(Integer partCount) {
        this.partCount = partCount;
    }
}
