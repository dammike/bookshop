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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(min = 10, max = 13, message = "Please enter a valid ISBN")
    @Column(unique = true)
    private String isbn;
    @NotEmpty(message = "Title Cannot be Blank")
    @Size(min = 2, message = "Please Enter a Valid Title")
    @Column(unique = true, nullable = false)
    private String title;
    @Column(length = 1050)
    private String summary;
    @Column
    private String shortSummary;
    private int pages;
    @Column(columnDefinition = "integer default 1")
    private int quantity;
    @Column(precision = 6)
    @Digits(integer = 6, fraction = 2, message = "Please enter a valid price")
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy")
    private Date published;
    @Lob
    private Byte[] coverImage;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "book condition required")
    private BookCondition bookCondition;
    @Column(columnDefinition = "boolean default true")
    private boolean available;
    @Column(columnDefinition = "boolean default false")
    private boolean withdrawn;
    private Date modified;

    public Book(Publisher publisher, String isbn, String title, String summary,
                int pages, BigDecimal price, Date published, BookCondition bookCondition) {
        this.publisher = publisher;
        this.isbn = isbn;
        this.title = title;
        this.summary = summary;
        this.pages = pages;
        this.price = price;
        this.published = published;
        this.bookCondition = bookCondition;
        this.available = true;
        this.withdrawn = false;
    }

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}

