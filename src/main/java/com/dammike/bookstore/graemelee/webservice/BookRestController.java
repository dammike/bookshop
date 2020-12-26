package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/books", "/books/{id}"})
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

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void addBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateBook(@RequestBody Book book, @PathVariable Long id) {
        log.debug("Updating Book: " + book);
        bookService.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book result = bookService.getBookById(id);
        bookService.deleteBook(result);
    }
}
