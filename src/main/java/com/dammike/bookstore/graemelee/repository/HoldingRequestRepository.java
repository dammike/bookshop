package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoldingRequestRepository extends JpaRepository<HoldingRequest, Long> {
    Optional<HoldingRequest> findByMemberId(Long memberId);
}
