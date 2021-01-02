package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import java.util.Date;

@Entity
@Data
public class OrderHistory extends BaseEntity{
    @ManyToOne
    private Consumer customer;
    @ManyToOne
    private Book book;

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}
