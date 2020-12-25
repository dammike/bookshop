package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class BookShelf extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Admin.class,
            optional = false)
    private Admin admin;
    @OneToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(nullable = false)
    private String isle;
    @Column(name="shelf_column", nullable = false)
    private String column;
    @Column(name = "shelf_row", nullable = false)
    private String row;
    private Date lastUpdated;
}
