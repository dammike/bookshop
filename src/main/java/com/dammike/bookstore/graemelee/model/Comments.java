package com.dammike.bookstore.graemelee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Comments extends BaseEntity{
    private String comment;
    @CreationTimestamp
    private Date commentedTime;
}
