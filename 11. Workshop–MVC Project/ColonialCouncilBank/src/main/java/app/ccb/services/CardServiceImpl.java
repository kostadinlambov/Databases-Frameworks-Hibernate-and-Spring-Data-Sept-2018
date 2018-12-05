package app.ccb.services;

import app.ccb.domain.dtos.cardDtos.CardImportDto;
import app.ccb.domain.dtos.cardDtos.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
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
public class CardServiceImpl implements CardService {
    private static final String CARDS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\xml\\cards.xml";

    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile(CARDS_FILE_PATH);
    }

    @Override
    public String importCards() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();
        CardImportRootDto cardImportRootDtos = this.xmlParser.parseXml(CardImportRootDto.class, CARDS_FILE_PATH);

        for (CardImportDto cardImportDto: cardImportRootDtos.getCardImportDtos()) {
            if (!this.validationUtil.isValid(cardImportDto)) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            BankAccount bankAccount = this.bankAccountRepository
                    .findByAccountNumber(cardImportDto.getBankAccountNumber())
                    .orElse(null);

            if(bankAccount == null){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Card card = this.modelMapper.map(cardImportDto, Card.class);
            card.setBankAccount(bankAccount);

            Card savedCard = this.cardRepository.saveAndFlush(card);

            if (savedCard != null) {
                importResult
                        .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, savedCard.getClass().getSimpleName(), savedCard.getCardNumber()))
                        .append(System.lineSeparator());
            }
        }

        return importResult.toString().trim();
    }
}
