package bookshopSystem.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;
    private Set<Category> categories;
    private Author author;

    public Book() {
        this.categories = new HashSet<>();
    }

    @Column(name = "title", length = 50, nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description",columnDefinition = "TEXT", length = 1000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    public EditionType getEditionType() {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "copies", nullable = false)
    public Integer getCopies() {
        return this.copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @ManyToMany()
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
