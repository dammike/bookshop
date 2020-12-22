package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
public class BookShelf extends BaseEntity {
    @OneToOne
    private Admin admin;
    @OneToOne
    private Book book;
    private String isle;
    private Date lastUpdated;
}
