package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
