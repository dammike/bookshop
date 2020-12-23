package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class User extends NamedEntity {
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedOut;
    @CreationTimestamp
    private Date joinedDate;
    @Lob
    private Object[] profilePhoto;
    @Column(columnDefinition = "boolean default false")
    private boolean deactivated;

    @Embedded
    private AddressDetails addressDetails;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "countryCode", column = @Column(name = "phoneCountryCode")),
            @AttributeOverride( name = "areaCode", column = @Column(name = "phoneAreaCode")),
            @AttributeOverride( name = "phoneNumber", column = @Column(name = "phonePhoneNumber"))
    })
    private ContactDetails phoneNumber;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "countryCode", column = @Column(name = "mobileCountryCode")),
            @AttributeOverride( name = "areaCode", column = @Column(name = "mobileAreaCode")),
            @AttributeOverride( name = "phoneNumber", column = @Column(name = "mobilePhoneNumber"))
    })
    private ContactDetails mobileNumber;
}
