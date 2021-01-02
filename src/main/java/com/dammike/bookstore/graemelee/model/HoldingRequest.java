package com.dammike.bookstore.graemelee.model;

import com.dammike.bookstore.graemelee.util.LongToConsumerConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <h2>A class for Requesting books when they are not available!</h2>
 * Book club members can request for books that are already borrowed/Loaned. Please refer to
 * {@link com.dammike.bookstore.graemelee.model.LoanBook}.
 * <p>
 * Holding Request will need deactivating when the user get the chance to own it.
 *
 * @author dammike
 * @see com.dammike.bookstore.graemelee.model.LoanBook
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class HoldingRequest extends SuperEntity
        implements Serializable {
    private static final Integer MAX_NUMBER_OF_REQUESTS_ALLOWED_FOR_BOOK = 2;

    @EmbeddedId
    private HoldingRequestPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Admin admin;

    @Column(columnDefinition = "boolean default false")
    private boolean expired;

    @Column(columnDefinition = "boolean default false")
    private boolean onlineRequest;

    public HoldingRequest(Admin admin, Consumer member, Book bookOfInterest) {
        HoldingRequestPK pk = new HoldingRequestPK();
        setAdmin(admin);
        pk.setMember(member);
        pk.setBookOfInterest(bookOfInterest);
        setExpired(false);
    }

    @Embeddable
    @Data
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class HoldingRequestPK implements Serializable {
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnoreProperties
        @Convert(converter = LongToConsumerConverter.class)
        private Consumer member;

        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnoreProperties
        private Book bookOfInterest;
    }

    /**
     * Only an Admin will have the authority to update a HoldingRequest row in the database.
     * Admin's timestamp will be recorded as an indication of the time when the HoldingRequest was approved on-behalf
     * of the member.
     */
    @PreUpdate
    public void onModification() {
        this.modified = new Date();
    }
}
