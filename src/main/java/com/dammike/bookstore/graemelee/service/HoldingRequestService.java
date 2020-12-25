package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoldingRequestService {
    @Autowired
    private HoldingRequestRepository holdingRequestRepository;

    public void save(HoldingRequest holdingRequest) {
        holdingRequestRepository.save(holdingRequest);
    }

    public void update(HoldingRequest holdingRequest) {
        HoldingRequest result = holdingRequestRepository.findById(holdingRequest.getId()).orElseThrow();
        result.setBookOfInterest(holdingRequest.getBookOfInterest());
        result.setRequestedDate(new Date());
        result.setMember(holdingRequest.getMember());
        holdingRequestRepository.save(result);
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
}
