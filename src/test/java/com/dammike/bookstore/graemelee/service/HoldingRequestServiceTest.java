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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HoldingRequestServiceTest {
    HoldingRequestService holdingRequestService;
    ConsumerService consumerService;
    BookService bookService;
    PublisherService publisherService;
    AdminService adminService;
    LoanBookService loanBookService;

    Admin admin;
    Consumer member;
    Publisher publisher;
    Book book;

    @Autowired
    public HoldingRequestServiceTest(HoldingRequestService holdingRequestService,
                                     ConsumerService consumerService,
                                     BookService bookService,
                                     PublisherService publisherService,
                                     AdminService adminService,
                                     LoanBookService loanBookService) {
        this.holdingRequestService = holdingRequestService;
        this.consumerService = consumerService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.adminService = adminService;
        this.loanBookService = loanBookService;
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
    public void testGetAllHoldingRequestsByCompositeKey() {
        when(holdingRequestRepository.findAllByIdMemberIdAndIdBookOfInterestId(member.getId(), book.getId()))
                .thenReturn(Stream.of(new HoldingRequest(admin, member, book))
                        .collect(Collectors.toList()));

        List<HoldingRequest> result = holdingRequestService.getHoldingRequestsForMemberWithBook(member, book);
        assertNotNull(result);
        assertEquals(1, result.size());
//        assertFalse(result.get(0)..HoldingRequestPK.isExpired());
//        assertTrue(result.get(0).getBookOfInterest().isAvailable());
    }

    @Test
    public void testSaveHoldingRequest() {
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


    @Test
    public void testRequestABook() throws Exception {
        // Preconditions
        book.setWithdrawn(false);
        book.setAvailable(false);
        member.setClubMember(true);

        // Pre-checks
        //1. Check if the Consumer is a registered 'clubMember'
        //2. Check if the book is 'withdrawn'
        //2. Book should not be 'available'.
        //  check whether requests exceed max value of 2 requests per book.
        //2 Max active holding-requests per book.The 3rd person member will not be allowed to apply for a
        //      HoldingRequest instead will asked to wait without any promise/gurantee.
        //2. Throw exception if member and book is present with active status 'true'
        //3. Save successfully

        // Member is a clubMember
        assertTrue(member.isClubMember());
        // check the book is withdrawn
        assertFalse(book.isWithdrawn());
        // confirm the book is still on loan by another customer
        assertFalse(book.isAvailable());
        if (book.isAvailable()) {
            throw new Exception("HoldingRequest not required. Book is already in the library and is ready to be Loaned.");
        }
            // check if the book already has 2 unexpired requests (this can be from any member)
            List<HoldingRequest> holdingRequests = holdingRequestService.getHoldingRequestsForBook(book);
            int size = holdingRequests.stream().filter(req -> !req.isExpired())
                    .collect(Collectors.toList()).size();

            assertNotEquals(2, size);
            if (size == 2) {
                throw new Exception("This book is fully booked");
            }

            // Dupllicate checks. Check if the book already have an 'active' holdinging request for this member and this book.
            List<HoldingRequest> result = holdingRequestService.getHoldingRequestsForMemberWithBook(member, book);
            boolean duplicate =  result.stream().filter(req -> !req.isExpired())
                    .collect(Collectors.toList()).size() > 0;
            if (duplicate) {
                throw new Exception("Attempting to make a duplicate holding request. There is an Holding request already" +
                        "made by you..");
            }

            // No more obstacles. Happy saving!
            HoldingRequest newRequest = new HoldingRequest(admin, member, book);
            holdingRequestService.save(newRequest);

    }


    @Test
    public void testAdminAuthorizeHoldingRequest() {
        // Pre-conditions
        HoldingRequest holdingRequest = new HoldingRequest(admin, member, book);
        holdingRequest.setExpired(false);
        holdingRequestService.save(holdingRequest);

        System.out.println("Printing...");
        holdingRequestRepository.findAll().forEach(a-> {
            System.out.println("Printing >>>>>" + a.toString());
        });

        // Pre-checks
        //4. set authorizedByAdmin to true
        //5. set authorizedTime
        //5. add entry to LoanBook
        //6. set holding request entry 'expired' to true;
        //7. Set book's availability to false
        //8. Send confirmation email to member.
        HoldingRequest.HoldingRequestPK key = holdingRequest.getId();
        adminService.authorizeHoldingRequest(admin, key);
    }
}