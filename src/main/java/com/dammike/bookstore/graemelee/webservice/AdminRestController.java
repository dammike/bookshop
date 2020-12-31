package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/admins")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/", "/{id}"})
    public List<Admin> getAllAdmins(@PathVariable(required = false) Long id) {
        List<Admin> admins = new ArrayList<>();
        if (id == null) {
            adminService.getAllAdmins().forEach(admins::add);
        } else {
            Admin admin = adminService.getAdminById(id);
            admins.add(admin);
        }
        return admins;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.save(admin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        adminService.save(admin);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
    }
}
