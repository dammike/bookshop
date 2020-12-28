package com.dammike.bookstore.graemelee.repository;

import com.dammike.bookstore.graemelee.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
