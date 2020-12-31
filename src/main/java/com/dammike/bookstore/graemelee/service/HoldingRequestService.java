package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class HoldingRequestService {
    @Autowired
    private HoldingRequestRepository holdingRequestRepository;

    public void save(HoldingRequest holdingRequest) {
        holdingRequestRepository.save(holdingRequest);
    }

    public void delete(Long id) {
        holdingRequestRepository.deleteById(id);
    }

    public HoldingRequest findById(Long id) {
        return holdingRequestRepository.findById(id).orElseThrow();
    }

    public List<HoldingRequest> findAll() {
        return holdingRequestRepository.findAll();
    }

    public HoldingRequest findMemberById(Long id) {
        return holdingRequestRepository.findByMemberId(id).orElseThrow();

    }

    public void requestBook(Admin admin, Consumer member, Book book) throws Exception {

        Set<HoldingRequest> holdingRequests = new HashSet<>();
        holdingRequestRepository.findAll().forEach(holdingRequests::add);
        boolean foundMatch = false;
        for (HoldingRequest req: holdingRequests) {
//            if (req.PgetMember().equals(member) && req.getBookOfInterest().equals(book)) {
//                throw new Exception("DuplicateHolding request already exists!");
//            }


        }

        HoldingRequest holdingRequest = new HoldingRequest(admin, member, book);
        holdingRequestRepository.save(holdingRequest);
    }
}
