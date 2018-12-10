package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {

    @XmlElement(name = "entry")
    private EntryImportDto[] entryImportDtos;

    public EntryImportRootDto() {
    }

    public EntryImportDto[] getEntryImportDtos() {
        return entryImportDtos;
    }

    public void setEntryImportDtos(EntryImportDto[] entryImportDtos) {
        this.entryImportDtos = entryImportDtos;
    }
}
