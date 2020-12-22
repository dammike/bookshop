package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.HoldingRequest;

import java.util.Collection;
import java.util.Optional;

public interface HoldingRequestService {
    void save(HoldingRequest holdingRequest);

    void update(HoldingRequest holdingRequest);

    void delete(HoldingRequest holdingRequest);

    Optional<HoldingRequest> findById(Long id);

    Collection<HoldingRequest> findAll();
}
