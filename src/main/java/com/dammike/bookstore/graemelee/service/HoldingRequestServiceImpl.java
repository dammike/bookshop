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
            this.repository.save(obj);

        } else {
            try {
                throw new Exception("No such holding request found for the given id: " + holdingRequest.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(HoldingRequest holdingRequest) {
        Optional<HoldingRequest> optional = this.repository.findById(holdingRequest.getId());
        if (optional.isPresent()) {
            this.repository.delete(holdingRequest);

        } else {
            try {
                throw new Exception("No such holding request found for the given id: " + holdingRequest.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<HoldingRequest> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Collection<HoldingRequest> findAll() {
        return this.repository.findAll();
    }
}
