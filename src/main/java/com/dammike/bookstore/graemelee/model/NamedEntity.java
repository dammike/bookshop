package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class NamedEntity extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
}
