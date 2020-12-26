package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HoldingRequest extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Consumer member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Book bookOfInterest;
    @CreationTimestamp
    private Date requestedDate;
    @Column(columnDefinition = "boolean default false")
    private boolean expired;

    public HoldingRequest(Consumer member, Book bookOfInterest) {
        setMember(member);
        setBookOfInterest(bookOfInterest);
    }
}
