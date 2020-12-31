package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.BookCondition;
import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookRestControllerTest {
    Publisher publisher = new Publisher("Random House Audio Produtions", "Specialized in podcasts");

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getAllBooksTest() {
        when(bookRepository.findAll())
                .thenReturn(Stream.of(
                        new Book(publisher, "123456890", "How to get Rich", "Or die trying",
                                210, new BigDecimal(10.0), new Date(), BookCondition.LIKE_NEW),

                        new Book(publisher, "9923454559", "Eat healthy 40 steps", "Eating healthy",
                                210, new BigDecimal(10.0), new Date(), BookCondition.POOR))
                        .collect(Collectors.toList())
                );

        assertEquals(2, bookService.getAllBooks().size());
    }

    @Test
    public void getBookByIdTest() {
        Book book = new Book(publisher, "123456890", "How to get Rich", "Or die trying",
                210, new BigDecimal(10.0), new Date(), BookCondition.LIKE_NEW);
        book.setId(1L);

        when(bookRepository.findById(1L))
                .thenReturn(
                        Optional.of(book)
                );

        assertEquals(1L, bookService.getBookById(1L).getId().longValue());
    }

    @Test
    public void saveBookTest() {
        Book book = new Book(publisher, "123456890", "How to get Rich", "Or die trying",
                210, new BigDecimal(10.0), new Date(), BookCondition.LIKE_NEW);
        bookRepository.save(book);
        verify(bookRepository, times(1))
                .save(any(Book.class));

        book = new Book(publisher, "9923454559", "Eat healthy 40 steps", "Eating healthy",
                210, new BigDecimal(10.0), new Date(), BookCondition.POOR);
        bookRepository.save(book);
        verify(bookRepository, times(2))
                .save(any(Book.class));
    }

    @Test
    public void deleteBookTest() {
        Book book = new Book(publisher, "123456890", "How to get Rich", "Or die trying",
                210, new BigDecimal(10.0), new Date(), BookCondition.LIKE_NEW);
        book.setId(1L);

        bookRepository.delete(book);
        verify(bookRepository, times(1))
                .delete(book);
    }
}