package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static app.ccb.common.Constants.INCORRECT_DATA_MESSAGE;
import static app.ccb.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\json\\clients.json";

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENTS_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {

        StringBuilder importResult = new StringBuilder();
        ClientImportDto[] clientImportDtos = this.gson.fromJson(clients, ClientImportDto[].class);

        for (ClientImportDto clientImportDto : clientImportDtos) {
            if(!this.validationUtil.isValid(clientImportDto)){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            String employeeFirstName = clientImportDto.getAppointedEmployee().split("\\s+")[0];
            String employeeLastName = clientImportDto.getAppointedEmployee().split("\\s+")[1];

            Employee employee = this.employeeRepository.findAllByFirstNameAndLastName(employeeFirstName, employeeLastName).orElse(null);

            if(employee == null){
                importResult
                        .append(INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            String fullName = clientImportDto.getFirstName() + " " + clientImportDto.getLastName();

            Client clientFromDb = this.clientRepository.findByFullName(fullName).orElse(null);
            if(clientFromDb == null){
                Client client = this.modelMapper.map(clientImportDto, Client.class);
                client.setFullName(fullName);
                client.getEmployees().add(employee);

                Client savedClient = this.clientRepository.saveAndFlush(client);
                if(savedClient != null){
                    importResult
                            .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, savedClient.getClass().getSimpleName(), savedClient.getFullName()))
                            .append(System.lineSeparator());
                }
            }
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        StringBuilder result = new StringBuilder();
        List<Client> clientList = this.clientRepository.findClientWithTheMostCards();

        Client familyGuy = clientList.get(0);

        result.append(String.format("Name: %s%nAge: %d%nBank Account Number: %s%nCards: ",
                familyGuy.getFullName(), familyGuy.getAge(), familyGuy.getBankAccount().getAccountNumber() ))
                .append(System.lineSeparator());

        familyGuy.getBankAccount().getCards().forEach(card -> {
            result.append(String.format("\tCard Number: %s", card.getCardNumber())).append(System.lineSeparator());
            result.append(String.format("\tStatus: %s", card.getCardStatus())).append(System.lineSeparator());
        });

        return result.toString().trim();
    }
}
