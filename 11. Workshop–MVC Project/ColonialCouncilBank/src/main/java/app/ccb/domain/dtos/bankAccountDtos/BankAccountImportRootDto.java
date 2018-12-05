package app.ccb.domain.dtos.bankAccountDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bank-accounts")
public class BankAccountImportRootDto {

    @XmlElement(name = "bank-account")
    private List<BankAccountImportDto>  bankAccountImportRootDtos;

    public BankAccountImportRootDto() {
    }

    public List<BankAccountImportDto> getBankAccountImportRootDtos() {
        return this.bankAccountImportRootDtos;
    }

    public void setBankAccountImportRootDtos(List<BankAccountImportDto> bankAccountImportRootDtos) {
        this.bankAccountImportRootDtos = bankAccountImportRootDtos;
    }
}
