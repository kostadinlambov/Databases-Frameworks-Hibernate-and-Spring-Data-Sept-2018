package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
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
import java.util.Arrays;
import java.util.List;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

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
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(racersFileContent, RacerImportDto[].class)).forEach(racerImportDto -> {
            Racer racerEntity = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);
            if (racerEntity != null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());

                return;
            }

            Town townEntity = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
            if (!this.validationUtil.isValid(racerImportDto) || townEntity == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                return;
            }

            racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
            racerEntity.setHomeTown(townEntity);
            this.racerRepository.saveAndFlush(racerEntity);

            importResult
                    .append(String
                            .format(Constants.SUCCESSFUL_IMPORT_MESSAGE, racerEntity.getClass().getSimpleName(), racerEntity.getName())
                    ).append(System.lineSeparator());

        });

        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder exportResult = new StringBuilder();
        List<Racer> racers = this.racerRepository.exportRacingCars();

        racers.stream().forEach(racer -> {
            exportResult.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator());
            if (racer.getAge() != null) {
                exportResult.append(String.format("Age: %d", racer.getAge())).append(System.lineSeparator());
            }

            exportResult.append("Cars:").append(System.lineSeparator());
            racer.getCars().stream().forEach(car -> {
                exportResult
                        .append(String.format("\t%s %s %d", car.getBrand(), car.getModel(), car.getYearOfProduction()))
                        .append(System.lineSeparator());
            });

            exportResult.append(System.lineSeparator());
        });

        return exportResult.toString().trim();
    }
}
