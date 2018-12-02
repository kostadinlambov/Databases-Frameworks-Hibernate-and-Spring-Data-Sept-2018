package com.kl.cardealer.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportRootDto {

    @XmlElement(name = "part")
    private List<PartImportDto> partImportDtos;

    public PartImportRootDto() {
    }

    public List<PartImportDto> getPartImportDtos() {
        return this.partImportDtos;
    }

    public void setPartImportDtos(List<PartImportDto> partImportDtos) {
        this.partImportDtos = partImportDtos;
    }
}
