package app.ccb.services;

import app.ccb.domain.dtos.bankAccountDtos.BankAccountImportDto;
import app.ccb.domain.dtos.bankAccountDtos.BankAccountImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static app.ccb.common.Constants.INCORRECT_DATA_MESSAGE;
import static app.ccb.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private static final String BANK_ACCOUNT_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\xml\\bank-accounts.xml";

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNT_FILE_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();
        BankAccountImportRootDto bankAccountImportRootDtos = this.xmlParser.parseXml(BankAccountImportRootDto.class, BANK_ACCOUNT_FILE_PATH);

        for (BankAccountImportDto bankAccountImportRootDto : bankAccountImportRootDtos.getBankAccountImportRootDtos()) {
            if (!this.validationUtil.isValid(bankAccountImportRootDto)) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Client client = this.clientRepository.findByFullName(bankAccountImportRootDto.getClientName()).orElse(null);

            if(client == null){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            BankAccount bankAccount = this.modelMapper.map(bankAccountImportRootDto, BankAccount.class);
            bankAccount.setClient(client);

            BankAccount savedBankAccount = this.bankAccountRepository.saveAndFlush(bankAccount);

            if (savedBankAccount != null) {
                importResult
                        .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, savedBankAccount.getClass().getSimpleName(), savedBankAccount.getAccountNumber()))
                        .append(System.lineSeparator());
            }
        }

        return importResult.toString().trim();
    }
}
