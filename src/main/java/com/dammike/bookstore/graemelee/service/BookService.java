package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public void updateBook(Book book) {
        log.debug("Updating Book: " + book.getId());
        bookRepository.save(book);
    }

    public Book getBookByTitle(String title) {
        Optional<Book> optional = bookRepository.findByTitleContaining(title);
        return optional.orElseThrow();
    }

    public void deleteBook(Book book) {
        Book result = bookRepository.findById(book.getId()).orElseThrow();
        bookRepository.delete(result);
    }

    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElseThrow();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
       return books;
    }

    public List<Publisher> getAllPublishersForBook(Book book) {
        List<Publisher> publishers = new ArrayList<>();
        publisherRepository.findAll().forEach(publishers::add);

        return publishers.stream().filter(publisher ->
                publisher.getId() == book.getPublisher().getId())
                .collect(Collectors.toList());
    }
}
