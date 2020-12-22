package com.dammike.bookstore.graemelee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
public class Admin extends User {
    @Temporal(TemporalType.TIME)
    private Date hoursOnline;

    public void resetHours() {
        //TODO: Reset hours every month/week
    }
}
