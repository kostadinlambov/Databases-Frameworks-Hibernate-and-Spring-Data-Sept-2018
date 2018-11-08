package com.minkov.entities;

import com.minkov.db.annotations.Column;
import com.minkov.db.annotations.Entity;
import com.minkov.db.annotations.PrimaryKey;

@Entity(name = "departments")
public class Department {
    @PrimaryKey(name = "id")
    long id;

    @Column(name = "name")
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "| " + getId() + " | " + getName() + " |";
    }
}
