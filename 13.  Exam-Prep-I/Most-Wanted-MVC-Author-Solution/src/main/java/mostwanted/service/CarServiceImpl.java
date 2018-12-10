package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CarServiceImpl implements CarService {

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/cars.json";

    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(carsFileContent, CarImportDto[].class)).forEach(carImportDto -> {
            Racer racerEntity = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);
            if (!this.validationUtil.isValid(carImportDto) || racerEntity == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                return;
            }

            Car carEntity = this.modelMapper.map(carImportDto, Car.class);
            carEntity.setRacer(racerEntity);
            this.carRepository.saveAndFlush(carEntity);

            String carImportResult = String.format("%s %s @ %d", carEntity.getBrand(), carEntity.getModel(), carEntity.getYearOfProduction());
            importResult
                    .append(String
                            .format(Constants.SUCCESSFUL_IMPORT_MESSAGE, carEntity.getClass().getSimpleName(), carImportResult))
                    .append(System.lineSeparator());
        });

        return importResult.toString().trim();
    }
}
