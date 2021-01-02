package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class BookShelf extends BaseEntity {
    @OneToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Admin.class)
    private Admin admin;
    @OneToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    @JsonBackReference(value = "bookshelf")
    private Book book;
    @Column(nullable = false)
    private String isle;
    @Column(name="shelf_column", nullable = false)
    private String column;
    @Column(name = "shelf_row", nullable = false)
    private String row;

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}
