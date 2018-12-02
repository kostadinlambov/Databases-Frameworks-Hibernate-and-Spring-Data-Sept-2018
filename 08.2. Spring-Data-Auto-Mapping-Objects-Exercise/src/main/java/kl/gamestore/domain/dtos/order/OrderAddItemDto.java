package kl.gamestore.domain.dtos.order;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrderAddItemDto {
    private String gameTitle;

    public OrderAddItemDto() {
    }

    public OrderAddItemDto(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    @NotNull(message = "Title name is required!")
    @Pattern(regexp = "[A-Z].*", message = "Title has to begin with an uppercase letter.")
    @Size(min = 3, max = 100, message = "Title size must have length between 3 and 100 symbols (inclusively).")
    public String getGameTitle() {
        return this.gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}
