package com.dammike.bookstore.graemelee.model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactDetails {
    private String countryCode;
    private String areaCode;
    private String phoneNumber;
}
