package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Author extends NamedEntity {
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Book> books = new HashSet<>();

    public Author(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
}
