package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.repository.HoldingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class HoldingRequestServiceImpl implements HoldingRequestService {
    @Autowired
    private HoldingRequestRepository repository;

    @Override
    public void save(HoldingRequest holdingRequest) {
        this.repository.save(holdingRequest);
    }

    @Override
    public void update(HoldingRequest holdingRequest) {
        Optional<HoldingRequest> optional = this.repository.findById(holdingRequest.getId());
        if (optional.isPresent()) {
            HoldingRequest obj = optional.get();
            obj.setMember(holdingRequest.getMember());
            obj.setBookOfInterest(holdingRequest.getBookOfInterest());
            obj.setRequestedDate(new Date());
        }
    }

    @Override
    public void delete(HoldingRequest holdingRequest) {

    }

    @Override
    public Optional<HoldingRequest> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<HoldingRequest> findAll() {
        return null;
    }
}
