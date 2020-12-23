package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class AddressDetails {
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
