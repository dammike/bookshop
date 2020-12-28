package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @BeforeEach
    public void setup() {
        Admin admin;
        admin = new Admin("Jlain", "Farqhuar", "jfk234", "nuggets", "farq@email.com");
        this.adminService.save(admin);
        admin = new Admin("James", "Clark", "fff", "111", "dom@gmail.com");
        this.adminService.save(admin);
    }

    @Test
    public void testSavingAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        assertNotNull(admins);
        assertEquals(2, admins.size());
        assertTrue(admins.get(1).getFirstName().equals("James"));
    }

    @Test
    public void testUpdatingAdmins() {
        Admin admin = adminService.getAdminByEmail("farq@email.com");
        admin.setEmail("something@yahoo.com");
        adminService.save(admin);
        assertThrows(Exception.class, () -> {
            adminService.getAdminByEmail("farq@email.com");
        });
        assertEquals("something@yahoo.com", adminService.getAdminByEmail("something@yahoo.com")
                .getEmail());
    }

    @Test
    public void testDeletingAdmins() {
        Admin admin;
        admin = adminService.getAdminByEmail("dom@gmail.com");
        assertNotNull(admin);
        adminService.delete(admin.getId());
        assertThrows(Exception.class, () -> {
            adminService.getAdminByEmail("dom@gmail.com");
        });
    }

    @AfterEach
    public void destroy() {
        adminService.getAllAdmins().forEach(admin -> {
            adminService.delete(admin.getId());
        });
    }
}