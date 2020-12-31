package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode
@IdClass(HoldingRequest.HoldingRequestId.class)
public class HoldingRequest extends BaseEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Consumer member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Book bookOfInterest;

    @Id
    @Column(columnDefinition = "boolean default false")
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    @NotBlank
    private Admin admin;

    @CreationTimestamp
    @Column(nullable = false)
    private Date requestedDate;


    @Data
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class HoldingRequestId implements Serializable {
        private Long id;
        private Consumer member;
        private Book bookOfInterest;
        private boolean expired;

        public HoldingRequestId(Consumer member, Book bookOfInterest, boolean expired) {
            this.member = member;
            this.bookOfInterest = bookOfInterest;
            this.expired = expired;
        }
    }


    public HoldingRequest(Admin admin, Consumer member, Book bookOfInterest) {
        setAdmin(admin);
        setMember(member);
        setBookOfInterest(bookOfInterest);
        setRequestedDate(new Date());
    }
}
