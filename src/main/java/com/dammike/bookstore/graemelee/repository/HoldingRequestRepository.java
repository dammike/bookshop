package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.model.HoldingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingRequestRepository extends JpaRepository<HoldingRequest, HoldingRequest.HoldingRequestPK> {
    Optional<HoldingRequest> findById(HoldingRequest.HoldingRequestPK pk);

    Optional<HoldingRequest> findByIdMemberId(Long memberId);

    List<HoldingRequest> findAllByIdBookOfInterest(Book book);

    List<HoldingRequest> findAllByIdMemberIdAndIdBookOfInterestId(Long memberId, Long bookId);

    Optional<HoldingRequest> findByIdMemberIdAndIdBookOfInterestIdAndExpired(Long memberId, Long bookId, Boolean flag);

    Optional<HoldingRequest> findByIdMemberAndIdBookOfInterestAndExpiredFalse(Consumer member, Book book);

    void deleteByIdMemberAndIdBookOfInterestAndExpired(Consumer member, Book book, boolean expired);
}
