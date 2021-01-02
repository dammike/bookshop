package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Slf4j
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
    @ElementCollection
    private List<String> commentHistory = new ArrayList<>();

    public Comments(Book book, Consumer consumer, String comment) {
        this.book = book;
        this.consumer = consumer;
        this.comment = comment;
    }

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}
