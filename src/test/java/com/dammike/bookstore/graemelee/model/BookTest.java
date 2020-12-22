package com.dammike.bookstore.graemelee.model;

import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.service.BookService;
import com.dammike.bookstore.graemelee.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class BookTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testCreateBook() {
        Book book = new Book("Jungle Book");
        book.setPrice(new BigDecimal(20.00));
        book.setPages(600);
        book.setDescription("some book");


        Book result = bookRepository.save(book);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("jungle book", result.getTitle());
    }
}