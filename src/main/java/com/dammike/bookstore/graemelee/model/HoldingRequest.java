package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class HoldingRequest extends BaseEntity {
    @ManyToOne
    private Consumer member;
    @ManyToOne
    private Book bookOfInterest;
    @CreationTimestamp
    private Date requestedDate;

    public HoldingRequest(Consumer member, Book bookOfInterest) {
        setMember(member);
        setBookOfInterest(bookOfInterest);
    }
}
