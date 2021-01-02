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
    private PublisherService service;

    @RequestMapping(method = RequestMethod.GET,
            value = "/")
    public String getAll(Model model) {
        List<Publisher> publishers = service.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publisher";
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/new")
    public String showNewPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "new_publisher_form";
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/save")
    public String save(@Valid @ModelAttribute("publisher") Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            if(publisher.getId() != null) {
                return "edit_publisher_form";
            }
            return "new_publisher_form";
        }
        service.save(publisher);
        log.debug("Saved Publisher[" + publisher.getId() + "]");
        return "redirect:/publisher/";
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/edit/{id}")
    public ModelAndView showEditPublisherForm(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("edit_publisher_form");
        Publisher publisher = service.getPublisherById(id);
        mv.addObject("publisher", publisher);
        return mv;
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        log.debug("Deleted publisher[" + id + "]");
        return "redirect:/publisher/";
    }
}
