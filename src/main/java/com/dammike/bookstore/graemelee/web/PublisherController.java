package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/")
    public String getAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publisher";
    }

    @RequestMapping("/new")
    public String showNewPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "new_publisher_form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String savePublisher(@Valid @ModelAttribute("publisher") Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            if(publisher.getId() != null) {
                return "edit_publisher_form";
            }
            return "new_publisher_form";
        }
        publisherService.save(publisher);
        log.debug("Saved Publisher[" + publisher.getId() + "]");
        return "redirect:/publisher/";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPublisherForm(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("edit_publisher_form");
        Publisher publisher = publisherService.getPublisherById(id);
        mv.addObject("publisher", publisher);
        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deletePublisher(@PathVariable(name = "id") Long id) {
        publisherService.delete(id);
        log.debug("Deleted publisher[" + id + "]");
        return "redirect:/publisher/";
    }
}
