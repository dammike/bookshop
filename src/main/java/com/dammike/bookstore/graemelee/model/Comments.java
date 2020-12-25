package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Comments extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Book.class,
            optional = false)
    private Book book;
    @ManyToOne(optional = false)
    private Consumer consumer;
    @Column(nullable = false)
    private String comment;
    @CreationTimestamp
    private Date commentedTime;
    private String adminReply;
}
