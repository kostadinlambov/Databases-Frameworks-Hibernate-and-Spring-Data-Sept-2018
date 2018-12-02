package com.kl.productshop.domain.dtos.seed_dtos;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDto {
    @XmlElement(name="name")
    private String name;

    public CategoryImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
