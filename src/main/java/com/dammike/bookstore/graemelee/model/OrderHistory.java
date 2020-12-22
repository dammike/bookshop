package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class OrderHistory extends BaseEntity{
    @ManyToOne
    private Consumer customer;
    @ManyToOne
    private Book book;
    @CreationTimestamp
    private Date orderPlacedOn;
}
