package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Consumer extends User {
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private boolean clubMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Consumer_Category",
    joinColumns = {@JoinColumn(name = "consumer_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> interestedCategories = new HashSet<>();
}
