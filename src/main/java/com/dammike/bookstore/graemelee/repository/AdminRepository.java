package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByEmailContaining(String email);
}
