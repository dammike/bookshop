package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Consumer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
    Optional<Consumer> findByEmailContaining(String email);
}
