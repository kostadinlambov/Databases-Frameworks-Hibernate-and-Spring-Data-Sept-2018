package com.kl.cardealer.domain.dtos.seed_dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="is-importer")
    private Boolean isImporter;

    public SupplierImportDto() {
    }

    @NotNull(message = "Name cannot be null!")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Importer cannot be null!")
    public Boolean getImporter() {
        return this.isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
