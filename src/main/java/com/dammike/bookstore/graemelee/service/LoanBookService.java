package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.LoanBook;
import com.dammike.bookstore.graemelee.repository.LoanBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanBookService {
    @Autowired
    private LoanBookRepository loanBookRepository;

    public List<LoanBook> getAllBookLoans() {
        List<LoanBook> loans = new ArrayList<>();
        loanBookRepository.findAll().forEach(loans::add);
        return loans;
    }

    public LoanBook getLoanBookById(Long id) {
        return loanBookRepository.findById(id).orElseThrow();
    }

    public void save(LoanBook loanBook) {
        loanBookRepository.save(loanBook);
    }

    public void deleteLoanBook(LoanBook loanBook) {
        loanBookRepository.delete(loanBook);
    }

    public List<LoanBook> getAllLoansForBook(Book book) {
        List<LoanBook> loans = loanBookRepository.findAllByBookOfInterest(book);
        return loans;
    }
}
