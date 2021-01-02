package com.dammike.bookstore.graemelee.util;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class LongToBookConverter  implements Converter<Long, Book> {

    @Autowired
    private BookService bookService;

    public Book convert(Long source) {
        return bookService.getBookById(source);
    }
}