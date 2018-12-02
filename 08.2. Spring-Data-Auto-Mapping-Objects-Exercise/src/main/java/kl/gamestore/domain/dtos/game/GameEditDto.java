package kl.gamestore.domain.dtos.game;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameEditDto {
    private Long id;
    private String title;
    private String trailer;
    private String thumbnailUrl;
    private BigDecimal size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameEditDto() {
    }

    public GameEditDto(Long id, String title, String trailer, String thumbnailUrl, BigDecimal size, BigDecimal price, String description, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @NotNull(message = "Id is required.")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Title name is required!")
    @Pattern(regexp = "[A-Z].*", message = "Title has to begin with an uppercase letter.")
    @Size(min = 3, max = 100, message = "Title size must have length between 3 and 100 symbols (inclusively).")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 11, max = 11, message = "Trailer ID must be exactly 11 characters")
    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/).*",message = "Thumbnail must start with 'http:' or 'https:'")
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @NotNull(message = "Size name is required!")
    @Digits(integer = 8, fraction = 1, message = "Size must be a positive number with precision up to 1 digits after the floating point.")
    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    @NotNull(message = "Price name is required!")
    @Digits(integer = 8, fraction = 2, message = "Price must be a positive number with precision up to 2 digits after the floating point.")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Description name is required!")
    @Size(min = 20, message = "Description length must be at least 20 symbols.")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
