package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

    @XmlElement(name = "race")
    private RaceImportDto[] raceImportDtos;

    public RaceImportRootDto() {
    }

    public RaceImportDto[] getRaceImportDtos() {
        return raceImportDtos;
    }

    public void setRaceImportDtos(RaceImportDto[] raceImportDtos) {
        this.raceImportDtos = raceImportDtos;
    }
}
