package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.raceImportDtos.EntryDto;
import mostwanted.domain.dtos.raceImportDtos.RaceImportDto;
import mostwanted.domain.dtos.raceImportDtos.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {
    private static final String RACES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\races.xml";

    private final RaceRepository raceRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final DistrictRepository districtRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository, DistrictRepository districtRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.raceRepository = raceRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();
        RaceImportRootDto raceImportRootDto = this.xmlParser.parseXml(RaceImportRootDto.class, RACES_FILE_PATH);

        for (RaceImportDto raceImportDto : raceImportRootDto.getRaceImportDtoList()) {
            if (!this.validationUtil.isValid(raceImportDto)) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District districtFromDb = this.districtRepository.findByName(raceImportDto.getDistrictName()).orElse(null);

            if(districtFromDb == null){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            List<RaceEntry> raceEntries = new ArrayList<>();

            for (EntryDto entryRootDto : raceImportDto.getEntryRootDto().getEntryDtoList()) {
                RaceEntry raceEntry = this.raceEntryRepository.findById(entryRootDto.getId()).orElse(null);

                if (raceEntry == null) {
                    importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

                raceEntries.add(raceEntry);
            }

            Race race = this.modelMapper.map(raceImportDto, Race.class);
            race.setDistrict(districtFromDb);
            race.setEntries(raceEntries);

            Race savedRace = this.raceRepository.saveAndFlush(race);

            importResult
                    .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, race.getClass().getSimpleName(), savedRace.getId()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();

    }
}
