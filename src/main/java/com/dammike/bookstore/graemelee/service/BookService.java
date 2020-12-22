package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;

import java.util.Collection;

public interface BookService {
    Book createBook(Book book);

    Book getBookById(Long id);

    Book updateBook(Book book);

    void deleteBook(Book book);

    Collection<Book> getAllBooks();
}
