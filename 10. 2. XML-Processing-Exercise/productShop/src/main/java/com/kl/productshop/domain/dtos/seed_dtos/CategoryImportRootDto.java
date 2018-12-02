package com.kl.productshop.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportRootDto {

    @XmlElement(name = "category")
    private List<CategoryImportDto> categoryImportDtoList;

    public CategoryImportRootDto() {
    }

    public List<CategoryImportDto> getCategoryImportDtoList() {
        return this.categoryImportDtoList;
    }

    public void setCategoryImportDtoList(List<CategoryImportDto> categoryImportDtoList) {
        this.categoryImportDtoList = categoryImportDtoList;
    }
}
