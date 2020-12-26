package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
