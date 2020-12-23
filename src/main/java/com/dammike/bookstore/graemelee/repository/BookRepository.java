package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findAllByISBN(String isbn);
}
