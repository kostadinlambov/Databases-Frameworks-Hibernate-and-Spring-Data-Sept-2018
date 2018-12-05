package app.ccb.domain.dtos.cardDtos;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportRootDto {

    @XmlElement(name = "card")
    private List<CardImportDto> cardImportDtos;

    public CardImportRootDto() {
    }

    public List<CardImportDto> getCardImportDtos() {
        return this.cardImportDtos;
    }

    public void setCardImportDtos(List<CardImportDto> cardImportDtos) {
        this.cardImportDtos = cardImportDtos;
    }
}
