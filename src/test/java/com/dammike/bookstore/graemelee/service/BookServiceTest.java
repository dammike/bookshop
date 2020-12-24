package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.BookCondition;
import com.dammike.bookstore.graemelee.model.Publisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @BeforeEach
    public void setup() {
        Publisher publisher = new Publisher("Wiley Productions", "Produces great content for technology products");
        bookService.addPublisher(publisher);
        Book book;
        book = new Book(publisher, "12345689", "How to get Rich", "Or die trying",
                210, new BigDecimal(10.0), new Date(), BookCondition.LIKE_NEW);
        bookService.createBook(book);
        book = new Book(publisher, "992345455", "Eat healthy 40 steps", "Eating healthy",
                210, new BigDecimal(10.0), new Date(), BookCondition.POOR);
        bookService.createBook(book);
    }

    @Test
    public void testAddingBook() {
        assertEquals(2, bookService.getAllBooks().size());
        assertEquals("992345455", bookService.getAllBooks().get(1).getISBN());
        assertEquals(BookCondition.POOR, bookService.getAllBooks().get(1).getBookCondition());
    }

    @Test
    public void testUpdatingBooks() {
        Book result = bookService.getBookByTitle("Eat healthy 40 steps");
        result.setTitle("Eat happily");
        bookService.updateBook(result);
        assertThrows(Exception.class, () -> {
            bookService.getBookByTitle("Eat healthy 40 steps");
        });
    }

    @AfterEach
    public void destroy() {
        Collection<Book> allBooks = bookService.getAllBooks();
        for (Book book : allBooks) {
            bookService.deleteBook(book);
        }
    }
}