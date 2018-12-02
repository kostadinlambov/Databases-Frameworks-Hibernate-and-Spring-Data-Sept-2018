package kl.gamestore.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{
    private String title;
    private String trailer;
    private String thumbnailUrl;
    private BigDecimal size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    private Set<User> buyers;

    public Game() {
        this.buyers = new HashSet<>();
    }

    @Column(name = "title", nullable = false, unique = true)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "trailer")
    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Column(name = "thumbnailUrl")
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }


    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Column(name = "size", precision = 8, scale = 1)
    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(BigDecimal size) {
        if(price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Size must be a positive number!");
        }
        this.size = size;
    }

    @Column(name = "price", precision = 8, scale = 2)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price must be a positive number!");
        }
        this.price = price;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "buyers_games",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    public Set<User> getBuyers() {
        return this.buyers;
    }

    public void setBuyers(Set<User> buyers) {
        this.buyers = buyers;
    }
}
