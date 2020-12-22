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

    @ManyToOne
    private Admin admin;
    @ManyToOne(cascade = CascadeType.ALL)
    private Consumer member;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book bookOfInterest;
    @CreationTimestamp
    private Date loanDate;
    @Transient
    private int daysRequired;
    @Temporal(TemporalType.DATE)
    private Date returnDate;


    public LoanBook(Admin admin, Consumer member, Book bookOfInterest, Date loadDate, Date returnDate) {
        setAdmin(admin);
        setMember(member);
        setBookOfInterest(bookOfInterest);
        setLoanDate(loadDate);
        setReturnDate(returnDate);
    }

    @PrePersist
    private void setReturnDate() {
        int standardLoanDays = 10;
        if (daysRequired > standardLoanDays) {
            setReturnDate(addDays(daysRequired));
        } else {
            setReturnDate(addDays(standardLoanDays));
        }

    }

    private Date addDays(int days) {
        LocalDateTime localDateTime = loanDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime.plusDays(days);
        return Date
                .from(localDateTime
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
