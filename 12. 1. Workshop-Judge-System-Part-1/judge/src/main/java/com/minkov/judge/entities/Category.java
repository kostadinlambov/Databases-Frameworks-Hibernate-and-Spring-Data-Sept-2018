package com.minkov.judge.entities;

import com.minkov.judge.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "categories")
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;
    private Category parentCategory;
    private Set<Category> subcategories;
    private Set<Contest> contests;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    @ManyToOne
    public Category getParentCategory() {
        return parentCategory;
    }

    @OneToMany(mappedBy = "parentCategory")
    public Set<Category> getSubcategories() {
        return subcategories;
    }

    @OneToMany(mappedBy = "category")
    public Set<Contest> getContests() {
        return contests;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }


    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}
