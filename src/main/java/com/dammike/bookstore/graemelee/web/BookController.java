package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "new_book_form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        log.debug("Saved Book[" + book.getId() + "]");
        return "redirect:/book/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditAdminForm(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("edit_book_form");
        Book book = bookService.getBookById(id);
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
