package com.kl.cardealer.domain.dtos.query4_dtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDto {

    @XmlElement(name = "car")
    private List<CarExportDto> carExportDtoList;

    public CarExportRootDto() {
    }

    public List<CarExportDto> getCarExportDtoList() {
        return this.carExportDtoList;
    }

    public void setCarExportDtoList(List<CarExportDto> carExportDtoList) {
        this.carExportDtoList = carExportDtoList;
    }
}
