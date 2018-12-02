package com.kl.productshop.domain.dtos.seed_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportRootDto {

    @XmlElement(name = "user")
    private List<UserImportDto> userImportDtoList;

    public UserImportRootDto() {
    }

    public List<UserImportDto> getUserImportDtoList() {
        return this.userImportDtoList;
    }

    public void setUserImportDtoList(List<UserImportDto> userImportDtoList) {
        this.userImportDtoList = userImportDtoList;
    }
}
