package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Category;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ItemImportDto {
    @Expose
    private String name;

    @Expose
    private String category;

    @Expose
    private BigDecimal price;

    public ItemImportDto() {
    }

    @NotNull
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @NotNull
    @Size(min = 3, max = 30)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotNull
    @Positive
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
