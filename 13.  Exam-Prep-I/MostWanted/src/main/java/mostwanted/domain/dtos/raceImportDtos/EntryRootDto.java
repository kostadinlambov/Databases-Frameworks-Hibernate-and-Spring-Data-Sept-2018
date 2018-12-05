package mostwanted.domain.dtos.raceImportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryRootDto {

    @XmlElement(name = "entry")
    private List<EntryDto> entryDtoList;

    public EntryRootDto() {
    }

    public List<EntryDto> getEntryDtoList() {
        return this.entryDtoList;
    }

    public void setEntryDtoList(List<EntryDto> entryDtoList) {
        this.entryDtoList = entryDtoList;
    }
}
