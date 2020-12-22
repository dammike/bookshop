package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRequestRepository extends PagingAndSortingRepository<HoldingRequest, Long> {
}
