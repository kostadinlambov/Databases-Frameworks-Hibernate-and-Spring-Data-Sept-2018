package app.ccb.services;

import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static app.ccb.common.Constants.INCORRECT_DATA_MESSAGE;
import static app.ccb.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class BranchServiceImpl implements BranchService {
    private static final String BRANCHES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\json\\branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesJson) {
        StringBuilder importResult = new StringBuilder();
        BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesJson, BranchImportDto[].class);

        for (BranchImportDto branchImportDto : branchImportDtos) {
            if(!this.validationUtil.isValid(branchImportDto)){
                importResult.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }


            Branch branch = this.modelMapper.map(branchImportDto, Branch.class);

            Branch savedBranch = this.branchRepository.saveAndFlush(branch);

            if(savedBranch != null){
                importResult
                        .append(String.format(SUCCESSFUL_IMPORT_MESSAGE, branch.getClass().getSimpleName(), branch.getName()))
                        .append(System.lineSeparator());
            }
        }


        return importResult.toString();
    }
}
