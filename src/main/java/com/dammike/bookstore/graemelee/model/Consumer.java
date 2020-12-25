package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Consumer extends User {
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(columnDefinition = "boolean default false")
    private boolean clubMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Consumer_Category",
    joinColumns = {@JoinColumn(name = "consumer_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> interestedCategories = new HashSet<>();

    public Consumer(String firstName, String lastName, String email, Date birthDate, boolean clubMember) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        this.birthDate = birthDate;
        this.clubMember = clubMember;
    }
}
