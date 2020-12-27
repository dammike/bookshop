package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Book.class)
@EqualsAndHashCode(exclude = {"authors", "categories", "publisher", "bookShelf"}, callSuper = true)
@ToString(exclude = {"authors", "categories", "publisher", "bookShelf"}, callSuper = true)
public class Book extends BaseEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Book_Category",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(targetEntity = Publisher.class, cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @JsonManagedReference(value = "bookshelf")
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private BookShelf bookShelf;

    @Column(unique = true)
    private String isbn;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(length = 1050)
    private String summary;
    @Column
    private String shortSummary;
    @Column(columnDefinition = "boolean default true")
    private boolean available;
    private int pages;
    @Column(columnDefinition = "integer default 1")
    private int quantity;
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    @Lob
    private Byte[] coverImage;
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;
    @Version
    @Column(name = "VERSION")
    private Long version = 0L;


    public Book(Publisher publisher, String isbn, String title, String summary,
                int pages, BigDecimal price, Date publishDate, BookCondition bookCondition) {
        this.publisher = publisher;
        this.isbn = isbn;
        this.title = title;
        this.summary = summary;
        this.pages = pages;
        this.price = price;
        this.publishDate = publishDate;
        this.bookCondition = bookCondition;
    }
}

