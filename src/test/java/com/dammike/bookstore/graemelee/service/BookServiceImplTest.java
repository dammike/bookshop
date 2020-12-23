package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Author;
import com.dammike.bookstore.graemelee.model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceImplTest {

    @BeforeAll
    public void setup() {

    }

    @Test
    public void testaddingBook() {
        Author author = new Author("Julian", "Farquahr");

        Book book = new Book();
    }
}