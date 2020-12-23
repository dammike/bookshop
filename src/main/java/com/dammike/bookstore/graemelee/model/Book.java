package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Book extends BaseEntity {
    @ManyToMany
    @JoinTable(name = "Book_Author",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();
    @ManyToOne
    private Publisher publisher;

    @Column(unique = true, nullable = true)
    private String ISBN;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(length = 1050)
    private String description;
    @Column(columnDefinition = "boolean default true")
    private boolean available;
    private int pages;
    @Column(columnDefinition = "integer default 1")
    private int quantity;
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    private Date dateIssued;

    @Embedded
    private BookCondition bookCondition;

    public Book(String title) {
        setTitle(title);
    }
}


