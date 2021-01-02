package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.*;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import com.dammike.bookstore.graemelee.util.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class HoldingRequestService {
    private HoldingRequestRepository repository;
    private LoanBookService loanBookService;

    @Autowired
    public HoldingRequestService(HoldingRequestRepository repository, LoanBookService loanBookService) {
        this.repository = repository;
        this.loanBookService = loanBookService;
    }

    public void save(HoldingRequest holdingRequest) {
        repository.save(holdingRequest);
    }


    public List<HoldingRequest> findAll() {
        return repository.findAll();
    }

    public HoldingRequest findMemberById(Long id) {
        return repository.findByIdMemberId(id).orElseThrow();

    }

    public void requestBook(Admin admin, Consumer member, Book book) throws Exception {

        Set<HoldingRequest> holdingRequests = new HashSet<>();
        repository.findAll().forEach(holdingRequests::add);
        boolean foundMatch = false;
        for (HoldingRequest req: holdingRequests) {
//            if (req.PgetMember().equals(member) && req.getBookOfInterest().equals(book)) {
//                throw new Exception("DuplicateHolding request already exists!");
//            }

        }

//        HoldingRequest holdingRequest = new HoldingRequest(admin, member, book);
//        holdingRequestRepository.save(holdingRequest);
    }/*
@Configuration
public class LongToConsumerConverter<T extends Consumer>
        implements Converter<Long, T> {

    @Autowired
    ConsumerService consumerService;

    public T convert(Long source) {
        return (T) consumerService.getConsumerById(source);
    }

}*/


    public List<HoldingRequest> getHoldingRequestsForMemberWithBook(Consumer member, Book book) {
        List<HoldingRequest> holdingRequest = repository
                .findAllByIdMemberIdAndIdBookOfInterestId(member.getId(), book.getId());
        return holdingRequest;
    }

    public List<HoldingRequest> getHoldingRequestsForBook(Book book) {
        return repository.findAllByIdBookOfInterest(book);
    }

    public void authorizeHoldingRequest(Admin admin, HoldingRequest.HoldingRequestPK key) {

        repository.findAll().forEach(a-> {
            System.out.println("Printing >>>>>" + a.toString());
        });

        HoldingRequest holdingRequest = repository
                .findByIdMemberAndIdBookOfInterestAndExpiredFalse(key.getMember(), key.getBookOfInterest()).orElseThrow();

        holdingRequest.setAdmin(admin);
        addEntryToLoanBook(holdingRequest.getAdmin(), key.getMember(), key.getBookOfInterest());

        holdingRequest.setExpired(true);
        key.getBookOfInterest()
                .setAvailable(false);
        repository.save(holdingRequest);

        Email.getInstance().sendConfirmation(key.getMember().getEmail());
    }

    private void addEntryToLoanBook(Admin admin, Consumer member, Book bookOfInterest) {
        LoanBook loanBook = new LoanBook(admin, member, bookOfInterest, new Date());
        loanBookService.save(loanBook);
    }

    public void delete(HoldingRequest holdingRequest) {
        /*holdingRequestRepository.deleteByMemberAndBookOfInterestAndExpired(
                holdingRequest.getMember(),
                holdingRequest.getBookOfInterest(),
                holdingRequest.isExpired());*/
    }

    public HoldingRequest getActiveHoldingRequestForMemberAndBook(Consumer member, Book book) {
        return repository.findByIdMemberIdAndIdBookOfInterestIdAndExpired(member.getId(), book.getId(),
                Boolean.FALSE).orElseThrow();
    }

    public HoldingRequest getHoldingRequestById(HoldingRequest.HoldingRequestPK id) {
        return repository.findById(id).orElseThrow();
    }
}
