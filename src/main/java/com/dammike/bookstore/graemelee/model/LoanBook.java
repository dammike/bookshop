package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class LoanBook extends BaseEntity {
    private static final Integer LOAN_PERIOD_IN_DAYS = 10;

    @ManyToOne
    private Admin admin;
    @ManyToOne(cascade = CascadeType.ALL)
    private Consumer member;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book bookOfInterest;
    @CreationTimestamp
    private Date loanDate;
    @Transient
    private Integer daysRequired;
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public LoanBook(Admin admin, Consumer member, Book bookOfInterest, Date loadDate) {
        setAdmin(admin);
        setMember(member);
        setBookOfInterest(bookOfInterest);
        setLoanDate(loadDate);

    }

    /**
     *  Overloaded constructor for when you want to implicitely extend the
     *  amount of days required*/
    public LoanBook(Admin admin, Consumer member, Book bookOfInterest, Date loadDate, Integer daysRequired) {
        this(admin, member, bookOfInterest, loadDate);
        setDaysRequired(daysRequired);
    }

    @PrePersist
    private void setReturnDate() {
        LocalDateTime dateTime;
        Date date;
        if (loanDate != null) {
            if (daysRequired > LOAN_PERIOD_IN_DAYS) {
                dateTime = LocalDateTime.now().plusDays(daysRequired);
                date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                setReturnDate(date);

            } else {
                dateTime = LocalDateTime.now().plusDays(LOAN_PERIOD_IN_DAYS);
                date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                setReturnDate(date);
            }
        }
    }

    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}