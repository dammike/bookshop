package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Book save(Book book) {
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

    public void delete(Long id) {
        Book result = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(result);
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return book;
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

    public void saveCoverImage(Long id, MultipartFile file) {
        try {
            Book book = bookRepository.findById(id).orElseThrow();
            Byte[] byteObject = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            book.setCoverImage(byteObject);
            bookRepository.save(book);

        } catch (IOException e) {
            log.error("Error occurred", e);
        }
    }
}
