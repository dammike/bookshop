package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.get();
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> optional = bookRepository.findById(book.getId());
        Book result = null;
        if (optional.isPresent()) {
            result = optional.get();
            result.setISBN(book.getISBN());
            result.setTitle(book.getTitle());
            result.setDescription(book.getDescription());
            result.setAvailable(book.isAvailable());
            result.setPages(book.getPages());
            result.setQuantity(book.getQuantity());
            result.setPrice(book.getPrice());
            result.setDateIssued(book.getDateIssued());

            bookRepository.save(result);
        } else {
            try {
                throw new Exception("No such object found!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public void deleteBook(Book book) {
        Optional<Book> optional = bookRepository.findById(book.getId());
        if (optional.isPresent()) {
            bookRepository.delete(book);
        } else {
            try {
                throw new Exception("No such object found!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Collection<Book> getAllBooks() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().forEach(books::add);
       return books;
    }
}
