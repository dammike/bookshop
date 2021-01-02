package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Publisher extends BaseEntity{
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50")
    private String name;
    private String description;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY,
            orphanRemoval = true, targetEntity = Book.class)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Publisher(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}
