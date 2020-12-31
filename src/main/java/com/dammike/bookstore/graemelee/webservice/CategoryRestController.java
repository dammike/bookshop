package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Category;
import com.dammike.bookstore.graemelee.service.BookService;
import com.dammike.bookstore.graemelee.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/categories")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
