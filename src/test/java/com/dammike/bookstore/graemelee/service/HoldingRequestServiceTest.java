package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HoldingRequestServiceTest {

    HoldingRequestService holdingRequestService;
    ConsumerService consumerService;
    BookService bookService;
    PublisherService publisherService;

    Consumer member;
    Book book;

    @Autowired
    public HoldingRequestServiceTest(HoldingRequestService holdingRequestService,
                                     ConsumerService consumerService,
                                     BookService bookService,
                                     PublisherService publisherService) {
        this.holdingRequestService = holdingRequestService;
        this.consumerService = consumerService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @BeforeEach
    public void setup() {
        member = new Consumer("Alex", "Riley", "dee@dee.com", new Date(), true);
        consumerService.createConsumer(member);
        Publisher publisher = new Publisher("WIley's productions", "Good IT books");
        publisherService.save(publisher);
        book = new Book(publisher, "JOJOJ223232", "Jungle Book", "Book about a jungle boy", 400,
                new BigDecimal(21), new Date(), BookCondition.EXCELLENT);
        bookService.createBook(book);
    }

    @Test
    public void testHoldRequestCreation() {

        HoldingRequest holdingRequest = new HoldingRequest(member, book);
        holdingRequestService.save(holdingRequest);

        HoldingRequest result = holdingRequestService.findMemberById(member.getId());
        assertNotNull(result);
        assertEquals("Jungle Book", result.getBookOfInterest().getTitle());
        assertEquals(1, holdingRequestService.findAll().size());
    }

    @AfterEach
    public void tearDown() {
        holdingRequestService.findAll().forEach(hr -> {
            holdingRequestService.delete(hr.getId());
        });
        consumerService.getAllConsumers().forEach(cs -> {
            consumerService.deleteConsumer(cs.getId());
        });
        bookService.getAllBooks().forEach(b -> {
            bookService.deleteBook(b);
        });
        publisherService.getAllPublishers().forEach(p -> {
            publisherService.delete(p.getId());
        });
    }
}