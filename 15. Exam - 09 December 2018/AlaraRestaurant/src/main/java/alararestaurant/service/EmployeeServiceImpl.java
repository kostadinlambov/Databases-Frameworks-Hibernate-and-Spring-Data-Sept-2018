package alararestaurant.service;


import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static alararestaurant.common.Constants.INVALID_DATA_FORMAT;
import static alararestaurant.common.Constants.SUCCESSFUL_JSON_IMPORT_MESSAGE;

@Service
//@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            if (!this.validationUtil.isValid(employeeImportDto)) {
                importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                continue;
            }

            Position position = this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);

            if (position == null) {
                position = new Position();
                position.setName(employeeImportDto.getPosition());
                this.positionRepository.saveAndFlush(position);
            }


            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setPosition(position);


            Employee savedEmployee = this.employeeRepository.saveAndFlush(employee);

            if (savedEmployee != null) {
                importResult
                        .append(String.format(SUCCESSFUL_JSON_IMPORT_MESSAGE, savedEmployee.getName()))
                        .append(System.lineSeparator());
            }
        }

        return importResult.toString().trim();
    }
}
