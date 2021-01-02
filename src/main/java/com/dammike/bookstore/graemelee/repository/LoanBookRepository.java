package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.LoanBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanBookRepository extends CrudRepository<LoanBook, Long> {
    List<LoanBook> findAllByBookOfInterest(Book book);
}
