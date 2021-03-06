package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Author;
import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Category;
import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.AuthorRepository;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.repository.CategoryRepository;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    BookService(BookRepository bookRepository, PublisherRepository publisherRepository,
                CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
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

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Set<Author> getAllAuthorsForBook(Book book) {
        Set<Author> authors = new HashSet<>();
        return authors;
    }
}
