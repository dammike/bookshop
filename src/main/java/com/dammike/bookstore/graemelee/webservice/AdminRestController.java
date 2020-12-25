package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/admins", "/admins/{id}"})
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

    @RequestMapping(method = RequestMethod.POST, value = "/admins")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admins/{id}")
    public void updateAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        Admin result = adminService.getAdminById(id);
        result.setFirstName(admin.getFirstName());
        result.setLastName(admin.getLastName());
        result.setEmail(admin.getEmail());
        result.setUsername(admin.getUsername());
        //todo: more mutators to be implemented here
        adminService.updateAdmin(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admins/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
