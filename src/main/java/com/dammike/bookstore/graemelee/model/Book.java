package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Book extends BaseEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Book_Author",
    joinColumns = @JoinColumn(name = "book_id", nullable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false, updatable = false))
    @JsonBackReference
    private Set<Author> authors = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Book_Category",
    joinColumns = @JoinColumn(name = "book_id", nullable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false, updatable = true))
    @JsonBackReference
    private List<Category> categories;
    @ManyToOne
    private Publisher publisher;
    @OneToOne(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private BookShelf storedLocation;
    @Column(unique = true, nullable = true)
    private String ISBN;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(length = 1050)
    private String description;
    @Column
    private String shortSummary;
    @Column(columnDefinition = "boolean default true")
    private boolean available;
    private int pages;
    @Column(columnDefinition = "integer default 1")
    private int quantity;
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    private Date publishDate;

    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;

    public Book(String title) {
        setTitle(title);
    }

    public Book(Publisher publisher, String ISBN, String title, String description,
                int pages, BigDecimal price,
                Date publishDate, BookCondition bookCondition) {
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.title = title;
        this.description = description;
        this.available = available;
        this.pages = pages;
        this.quantity = quantity;
        this.price = price;
        this.publishDate = publishDate;
        this.bookCondition = bookCondition;
    }
}


