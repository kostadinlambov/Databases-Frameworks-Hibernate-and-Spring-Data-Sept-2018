package com.kl.cardealer.domain.dtos.query2_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDtoQuery2 {

    @XmlElement(name = "car")
    private List<CarExportDtoQuery2> carExportDtoQuery2List;

    public CarExportRootDtoQuery2() {
    }

    public List<CarExportDtoQuery2> getCarExportDtoQuery2List() {
        return this.carExportDtoQuery2List;
    }

    public void setCarExportDtoQuery2List(List<CarExportDtoQuery2> carExportDtoQuery2List) {
        this.carExportDtoQuery2List = carExportDtoQuery2List;
    }
}
