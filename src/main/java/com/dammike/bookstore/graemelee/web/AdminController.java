package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.service.AdminService;
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
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/")
    public String getAllAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin";
    }

    @RequestMapping("/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "new_admin_form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if(admin.getId() != null) {
                return "edit_admin_form";
            }
            return "new_admin_form";
        }

        if (admin.getJoinedDate() == null) {
            admin.setJoinedDate(new Date());
        }
        adminService.save(admin);
        log.debug("Saved Admin[" + admin.getId() + "]");
        return "redirect:/admin/";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView showEditAdminForm(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("edit_admin_form");
        Admin admin = adminService.getAdminById(id);
        mv.addObject("admin", admin);
        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable(name = "id") Long id) {
        adminService.delete(id);
        log.debug("Deleted Admin[" + id + "]");
        return "redirect:/admin/";
    }
}
