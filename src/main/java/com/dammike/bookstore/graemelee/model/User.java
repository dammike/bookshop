package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class User extends NamedEntity {
    @Column(unique = true)
    @Size(min = 3, max = 30)
    private String username;
    @Size(min = 3, max = 50)
    private String password;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(nullable = false, unique = true)
    @Email(message = "Please provide a valid email address.",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedOut;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date joinedDate;
    @Lob
    private Byte[] profilePhoto;
    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

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
