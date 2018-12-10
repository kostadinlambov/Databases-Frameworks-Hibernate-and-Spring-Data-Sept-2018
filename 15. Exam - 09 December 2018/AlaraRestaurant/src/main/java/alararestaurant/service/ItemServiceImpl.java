package alararestaurant.service;

import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static alararestaurant.common.Constants.INVALID_DATA_FORMAT;
import static alararestaurant.common.Constants.SUCCESSFUL_JSON_IMPORT_MESSAGE;

@Service
public class ItemServiceImpl implements ItemService {
    private static final String ITEMS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean itemsAreImported() {

        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(ITEMS_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder importResult = new StringBuilder();
        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);

        for (ItemImportDto itemImportDto : itemImportDtos) {
            if (!this.validationUtil.isValid(itemImportDto)) {
                importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                continue;
            }

            Item item = this.itemRepository.findByName(itemImportDto.getName()).orElse(null);

            if (item != null) {
                importResult.append(INVALID_DATA_FORMAT).append(System.lineSeparator());
                continue;
            }

            Category category = this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);

            if(category == null){
                category = new Category();
                category.setName(itemImportDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }


            Item itemToSave = this.modelMapper.map(itemImportDto, Item.class);
            itemToSave.setCategory(category);

            Item savedItem = this.itemRepository.saveAndFlush(itemToSave);

            if (savedItem != null) {
                importResult
                        .append(String.format(SUCCESSFUL_JSON_IMPORT_MESSAGE, savedItem.getName()))
                        .append(System.lineSeparator());
            }
        }

        return importResult.toString().trim();
    }
}
