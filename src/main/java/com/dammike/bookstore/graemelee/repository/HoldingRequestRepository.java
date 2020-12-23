package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRequestRepository extends JpaRepository<HoldingRequest, Long> {
}
