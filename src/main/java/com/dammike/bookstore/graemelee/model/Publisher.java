package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Publisher extends BaseEntity{
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50")
    private String name;
    private String description;

    public Publisher(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
