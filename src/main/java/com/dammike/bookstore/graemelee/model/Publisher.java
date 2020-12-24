package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Publisher extends BaseEntity{
    private String name;
    private String description;

    public Publisher(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
