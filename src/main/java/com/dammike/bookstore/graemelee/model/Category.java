package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Category extends BaseEntity {
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Book> books;
    private String name;
}
