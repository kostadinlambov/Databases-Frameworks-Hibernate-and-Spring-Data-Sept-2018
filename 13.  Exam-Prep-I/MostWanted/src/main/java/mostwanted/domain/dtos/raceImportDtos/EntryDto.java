package mostwanted.domain.dtos.raceImportDtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryDto {
    @XmlAttribute(name = "id")
    private Integer id;

    public EntryDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
