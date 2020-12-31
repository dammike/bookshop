package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.*;
import com.dammike.bookstore.graemelee.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"", "/{id}"})
    public List<Book> getAllBooks(@PathVariable(required = false) Long id) {
        List<Book> books = new ArrayList<>();
        if (id == null) {
            bookService.getAllBooks().forEach(books::add);
        } else {
            Book book = bookService.getBookById(id);
            books.add(book);
        }
        return books;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public void addBook(@RequestBody Book book) {
        bookService.save(book);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateBook(@RequestBody Book book, @PathVariable Long id) {
        log.debug("Updating Book: " + book);
        bookService.save(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/{bookId}/publisher")
    public Publisher getPublisherForBook(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return book.getPublisher();
    }

    @GetMapping("/{bookId}/categories")
    public Set<Category> getCategoriesForBook(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return book.getCategories();
    }

    @GetMapping("/{bookId}/authors")
    public Set<Author> getAuthorsforBook(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return book.getAuthors();
    }

    @GetMapping("/{bookId}/bookcondition")
    public BookCondition getBookConditionForBook(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return book.getBookCondition();
    }
}
