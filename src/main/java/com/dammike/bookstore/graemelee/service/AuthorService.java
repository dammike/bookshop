package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Author;
import com.dammike.bookstore.graemelee.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Author getAuthorById(Long id) {
        Author author = authorRepository.findById(id).get();
        return author;
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void update(Author author) {
        authorRepository.save(author);
    }

    public void delete(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
