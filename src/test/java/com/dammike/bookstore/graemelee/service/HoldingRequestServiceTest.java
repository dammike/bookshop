package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.*;
import com.dammike.bookstore.graemelee.repository.BookRepository;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HoldingRequestServiceTest {
    HoldingRequestService holdingRequestService;
    ConsumerService consumerService;
    BookService bookService;
    PublisherService publisherService;
    AdminService adminService;

    Admin admin;
    Consumer member;
    Publisher publisher;
    Book book;

    @Autowired
    public HoldingRequestServiceTest(HoldingRequestService holdingRequestService,
                                     ConsumerService consumerService,
                                     BookService bookService,
                                     PublisherService publisherService,
                                     AdminService adminService) {
        this.holdingRequestService = holdingRequestService;
        this.consumerService = consumerService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.adminService = adminService;
    }

    @MockBean
    private HoldingRequestRepository holdingRequestRepository;
    @MockBean
    private BookRepository bookRepository;


    @BeforeEach
    public void setup() {
        member = new Consumer("Alex", "Riley", "dee@dee.com", new Date(), true);
        publisher = new Publisher("WIley's productions", "Good IT books");
        book = new Book(publisher, "JOJOJ223232", "Jungle Book", "Book about a jungle boy", 400,
                new BigDecimal(21), new Date(), BookCondition.EXCELLENT);
        admin = new Admin("Dominik", "Selchert", "dee", "password", "dominik@gmail.com");
    }


    @Test
    public void testGetAllHoldingRequests() {
        when(holdingRequestRepository.findAll())
                .thenReturn(Stream.of(new HoldingRequest(admin, member, book))
                        .collect(Collectors.toList()));
        assertEquals(1, holdingRequestService.findAll().size());
    }

    @Test
    public void testGetAllHoldingRequestsById() {
        when(holdingRequestRepository.findById(1L))
                .thenReturn(Optional.of(new HoldingRequest(admin, member, book)));

        assertNotNull(holdingRequestService.findById(1L));
        assertEquals(1L, holdingRequestService.findById(1L));
    }

    @Test
    public void testSaveHoldingRequest() {
        // Conditions
        //1. Check if the request already exists AND not expired.
        //2. Check if the Book is available for borrowing
        //3. Save holding request
        //4. Set book's availability to false

        HoldingRequest holdingRequest = new HoldingRequest(admin, member, book);
        holdingRequestRepository.save(holdingRequest);
        verify(holdingRequestRepository, times(1))
                .save(any(HoldingRequest.class));
    }

    @Test
    public void testDeleteHoldingRequest() {
        holdingRequestRepository.delete(new HoldingRequest(admin, member, book));
        verify(holdingRequestRepository, times(1))
                .delete(any(HoldingRequest.class));
    }
}