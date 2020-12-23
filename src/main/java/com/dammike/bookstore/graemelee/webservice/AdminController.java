package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin")
    public Collection<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/{id}")
    public void updateAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        Admin result = adminService.getAdminById(id);
        result.setFirstName(admin.getFirstName());
        result.setLastName(admin.getLastName());
        result.setEmail(admin.getEmail());
        result.setUsername(admin.getUsername());
        //todo: more mutators to be implemented here
        adminService.updateAdmin(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
