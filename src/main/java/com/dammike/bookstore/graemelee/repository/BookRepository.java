package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Iterable<Book> findAllByIsbn(String isbn);
    Optional<Book> findByTitleContaining(String title);
}
