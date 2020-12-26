package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Reviews extends BaseEntity {
    @ManyToOne
    private Consumer user;
    @ManyToOne
    private Book book;
    private String text;
    @CreationTimestamp
    private Date placedOn;
    private int rating;
}
