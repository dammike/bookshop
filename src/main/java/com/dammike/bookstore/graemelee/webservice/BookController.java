package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
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

    @RequestMapping(method = RequestMethod.PUT, value = "/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable Long id) {
        Book result = bookService.getBookById(id);
        result.setPublisher(book.getPublisher());
        result.setTitle(book.getTitle());
        result.setDescription(book.getDescription());
        result.setPublishDate(book.getPublishDate());
        result.setPages(book.getPages());
        //todo: more implementations
        bookService.createBook(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book result = bookService.getBookById(id);
        bookService.deleteBook(result);
    }
}
