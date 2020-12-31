package com.dammike.bookstore.graemelee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Category.class)
@EqualsAndHashCode(exclude = {"books"}, callSuper = true)
@ToString(exclude = {"books"})
public class Category extends BaseEntity {
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();
    private String name;
}
