package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    BookService(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Optional<Book> optional = bookRepository.findById(book.getId());
        Book result = optional.orElseThrow();
            result.setISBN(book.getISBN());
            result.setTitle(book.getTitle());
            result.setDescription(book.getDescription());
            result.setAvailable(book.isAvailable());
            result.setPages(book.getPages());
            result.setQuantity(book.getQuantity());
            result.setPrice(book.getPrice());
        result.setPublishDate(book.getPublishDate());

        bookRepository.save(result);

        return result;
    }

    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElseThrow();
    }
    public Book getBookByTitle(String title) {
        Optional<Book> optional = bookRepository.findByTitleContaining(title);
        return optional.orElseThrow();
    }

    public void deleteBook(Book book) {
        Book result = bookRepository.findById(book.getId()).orElseThrow();
        bookRepository.delete(result);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
       return books;
    }

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
