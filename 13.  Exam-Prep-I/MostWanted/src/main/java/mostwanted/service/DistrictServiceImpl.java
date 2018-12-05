package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static mostwanted.common.Constants.*;

@Service
public class DistrictServiceImpl implements DistrictService {
    private static final String DISTRICTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICTS_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder importResult = new StringBuilder();

        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);

        for (DistrictImportDto districtImportDto : districtImportDtos) {
            if(!this.validationUtil.isValid(districtImportDto)){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townDb = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);

            if(townDb == null){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District districtDb = this.districtRepository.findByName(districtImportDto.getName()).orElse(null);

            if(districtDb != null){
                importResult.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District district = this.modelMapper.map(districtImportDto, District.class);
            district.setTown(townDb);

            this.districtRepository.saveAndFlush(district);

            importResult
                    .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, district.getClass().getSimpleName(), district.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
