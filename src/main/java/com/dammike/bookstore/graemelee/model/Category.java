package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Category extends BaseEntity {
    private String name;
}
