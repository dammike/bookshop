package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class AddressDetails {
    private String unitNumber;
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String city;
    private String province;
    private String zipCode;
    private String country;
}
