package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Author extends NamedEntity {
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
