package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.Author;
import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.BookCondition;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import com.dammike.bookstore.graemelee.service.AuthorService;
import com.dammike.bookstore.graemelee.service.BookService;
import com.dammike.bookstore.graemelee.service.CategoryService;
import com.dammike.bookstore.graemelee.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;
    private CategoryService categoryService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService,
                          CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        model.addAttribute("publisherList", publisherService.getAllPublishers());
        model.addAttribute("authorList", authorService.getAllAuthors());
    }

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/new")
    public String showNewBookForm() {
        return "new_book_form";
    }

    @PostMapping("/save")
    @Transactional
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (book.getId() != null) {
                return "edit_book_form";
            }
            return "new_book_form";
        }
        log.debug("Saving Book_Author for Book: [" + book.getAuthors() +"], ");
        log.debug("Saving Publisher for Book: [" + book.getPublisher() +"], ");
        bookService.save(book);
        log.debug("Saved Book[" + book.getId() + "]");
        return "redirect:/book/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditAdminForm(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("edit_book_form");
        Book book = bookService.getBookById(id);
        log.debug("Ready to edit Book[" + book.getId() + "]");
        mv.addObject("book", book);
        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id) {
        bookService.delete(id);
        log.debug("Deleted Book[" + id + "]");
        return "redirect:/book/";
    }

    @GetMapping("/cover/new")
    public String showNewCoverForm(Model model) {
        model.addAttribute("book", new Book());
        return "new-book-cover";
    }

    @PostMapping("/{id}/cover/save")
    public String handleImagePost(@PathVariable(required = false) Long id, @RequestParam("imageFile") MultipartFile file) {
        if (id == null) {

        }
        bookService.saveCoverImage(id, file);
        return "redirect:/book/";
    }
}
