package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Publisher extends BaseEntity{
    private String name;
}
