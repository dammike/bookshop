package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
