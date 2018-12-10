package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDto {

    @XmlElement(name = "race-entry")
    private RaceEntryImportDto[] raceEntryImportDtos;

    public RaceEntryImportRootDto() {
    }

    public RaceEntryImportDto[] getRaceEntryImportDtos() {
        return raceEntryImportDtos;
    }

    public void setRaceEntryImportDtos(RaceEntryImportDto[] raceEntryImportDtos) {
        this.raceEntryImportDtos = raceEntryImportDtos;
    }
}
