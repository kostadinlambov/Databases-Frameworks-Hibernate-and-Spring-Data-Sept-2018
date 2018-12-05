package mostwanted.domain.dtos.raceImportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

    @XmlElement(name = "race")
    private List<RaceImportDto> raceImportDtoList;

    public RaceImportRootDto() {
    }

    public List<RaceImportDto> getRaceImportDtoList() {
        return this.raceImportDtoList;
    }

    public void setRaceImportDtoList(List<RaceImportDto> raceImportDtoList) {
        this.raceImportDtoList = raceImportDtoList;
    }
}
