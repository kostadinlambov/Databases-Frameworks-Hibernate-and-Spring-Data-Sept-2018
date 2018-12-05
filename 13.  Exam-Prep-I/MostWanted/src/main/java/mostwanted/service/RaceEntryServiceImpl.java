package mostwanted.service;

import mostwanted.domain.dtos.raceEntryDtos.RaceEntryImportDto;
import mostwanted.domain.dtos.raceEntryDtos.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private static final String RACE_ENTRIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, XmlParser xmlParser, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();
        RaceEntryImportRootDto raceEntryImportRootDto = this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_FILE_PATH);

        for (RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtoList()) {
            if (!this.validationUtil.isValid(raceEntryImportDto)) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car carFromDb = this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);
            Racer racerFromDb  = this.racerRepository.findByName(raceEntryImportDto.getRacerName()).orElse(null);

            if(carFromDb == null || racerFromDb == null){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            RaceEntry raceEntry = this.modelMapper.map(raceEntryImportDto, RaceEntry.class);

            raceEntry.setCar(carFromDb);
            raceEntry.setRacer(racerFromDb);


            RaceEntry savedRaceEntry = this.raceEntryRepository.saveAndFlush(raceEntry);

            if (savedRaceEntry != null){
                importResult
                        .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, raceEntry.getClass().getSimpleName(), savedRaceEntry.getId()))
                        .append(System.lineSeparator());
            }else{
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }

        }

        return importResult.toString().trim();
    }
}
