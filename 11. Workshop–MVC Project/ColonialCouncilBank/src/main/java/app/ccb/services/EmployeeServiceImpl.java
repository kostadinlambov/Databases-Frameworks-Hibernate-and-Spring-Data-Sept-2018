package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static app.ccb.common.Constants.INCORRECT_DATA_MESSAGE;
import static app.ccb.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();
        EmployeeImportDto[] employeesDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        for (EmployeeImportDto employeesDto : employeesDtos) {
            if(!this.validationUtil.isValid(employeesDto)){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Branch branch = this.branchRepository.findByName(employeesDto.getBranchName()).orElse(null);

            if(branch == null){
                importResult
                        .append(INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            String firstName = employeesDto.getFullName().split("\\s+")[0];
            String lastName = employeesDto.getFullName().split("\\s+")[1];

            Employee employee = this.modelMapper.map(employeesDto, Employee.class);
            employee.setStartedOn(LocalDate.parse(employeesDto.getStartedOn()));
            employee.setBranch(branch);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);

            Employee savedEmployee = this.employeeRepository.saveAndFlush(employee);

            if(savedEmployee != null){
                String message = String.format("%s %s", savedEmployee.getFirstName(), savedEmployee.getLastName());

                importResult
                        .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, savedEmployee.getClass().getSimpleName(), message))
                        .append(System.lineSeparator());
            }
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        StringBuilder result = new StringBuilder();

        List<Employee> employees = this.employeeRepository.findAllEmployeesAndOrder();

        final int[] count = {0};

        employees.forEach(employee -> {
            count[0]++;
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            result.append(String.format("%d.Employee: %s", count[0],fullName)).append(System.lineSeparator());
            result.append(String.format("\tEmployee id: %s", employee.getId())).append(System.lineSeparator());
            result.append(String.format("\tCount of Clients: %s", employee.getClients().size())).append(System.lineSeparator());
            result.append(System.lineSeparator());
        });

        return result.toString().trim();
    }
}
