package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class RacerServiceImpl implements RacerService {
    private static final String RACERS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder importResult = new StringBuilder();
        RacerImportDto[] racerImportDtos = this.gson.fromJson(racersFileContent, RacerImportDto[].class);

        for (RacerImportDto racerImportDto : racerImportDtos) {
            if (!this.validationUtil.isValid(racerImportDto)) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town homeTownDb = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);

            if (homeTownDb == null) {
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racerFromDb = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);

            if(racerFromDb != null){
                importResult.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racer = this.modelMapper.map(racerImportDto, Racer.class);
            racer.setHomeTown(homeTownDb);

            this.racerRepository.saveAndFlush(racer);

            importResult
                    .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, racer.getClass().getSimpleName(), racer.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder result = new StringBuilder();
        List<Racer> racers = this.racerRepository.allRacersWithCars();

        racers.forEach(racer -> {
                result.append("Name: " + racer.getName()).append(System.lineSeparator());
                if(racer.getAge() != null){
                    result.append("Age: " + racer.getAge()).append(System.lineSeparator());
                }

                if(racer.getCars().size() > 0){
                    result.append("Cars: ").append(System.lineSeparator());
                    racer.getCars().forEach(car -> {
                        result
                                .append(String.format("%s %s %s", car.getBrand(), car.getModel(), car.getYearOfProduction()))
                                .append(System.lineSeparator());
                    });
                }

                result.append(System.lineSeparator());
        });

        return result.toString().trim();
    }
}
