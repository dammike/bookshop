package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Book.class)
@EqualsAndHashCode(exclude = {"authors", "categories", "publisher", "bookShelf"}, callSuper = false)
public class Book extends BaseEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Book_Author",
    joinColumns = @JoinColumn(name = "book_id", nullable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false, updatable = false))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Book_Category",
    joinColumns = @JoinColumn(name = "book_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    @JsonManagedReference(value = "bookshelf")
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private BookShelf bookShelf;

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


    public Book(Publisher publisher, String ISBN, String title, String description,
                int pages, BigDecimal price, Date publishDate, BookCondition bookCondition) {
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.price = price;
        this.publishDate = publishDate;
        this.bookCondition = bookCondition;
    }
}


