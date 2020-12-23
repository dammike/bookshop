package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public Collection<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        return bookService.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void addBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    /*@RequestMapping(value = "/books", method = RequestMethod.GET)
    Collection<Book> findAll(@RequestParam(required = false) Long bookId) {
        List<Book> books = new ArrayList<>();
        if (bookId == null) {
            Iterable<Book> results = this.bookRepository.findAll();
            results.forEach(b -> {
                books.add(b);
            });
        } else {
            Optional<Book> result = this.bookRepository.findById(bookId);
            if (result.isPresent()) {
                books.add(result.get());
            }
        }
        return books;
    }*/
}
