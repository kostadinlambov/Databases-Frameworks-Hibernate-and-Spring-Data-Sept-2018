package com.kl.cardealer.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportRootDto {

    @XmlElement(name = "car")
    List<CarImportDto> carImportDtoList;

    public CarImportRootDto() {
    }

    public List<CarImportDto> getCarImportDtoList() {
        return this.carImportDtoList;
    }

    public void setCarImportDtoList(List<CarImportDto> carImportDtoList) {
        this.carImportDtoList = carImportDtoList;
    }
}
