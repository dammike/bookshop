package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Admin extends User {
    @Temporal(TemporalType.TIME)
    private Date totalHrsOnline;

    public Admin(String firstName, String lastName, String username, String password, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setJoinedDate(new Date());
    }
}