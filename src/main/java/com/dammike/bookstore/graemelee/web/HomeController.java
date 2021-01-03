package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.service.AuthorService;
import com.dammike.bookstore.graemelee.service.BookService;
import com.dammike.bookstore.graemelee.service.CategoryService;
import com.dammike.bookstore.graemelee.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private BookService service;
    private PublisherService publisherService;
    private CategoryService categoryService;
    private AuthorService authorService;

    @Autowired
    public HomeController(BookService service, PublisherService publisherService,
                          CategoryService categoryService, AuthorService authorService) {
        this.service = service;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @ModelAttribute
    private void addAttributes(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        model.addAttribute("publisherList", publisherService.getAllPublishers());
        model.addAttribute("authorList", authorService.getAllAuthors());
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/book/";
    }
}
