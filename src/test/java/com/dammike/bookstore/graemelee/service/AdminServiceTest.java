package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    Admin admin;

    @BeforeEach
    public void setup() {
        admin = new Admin("Julain", "Farqhuar", "jfk234", "nuggets", "farq@email.com");
        //admin = new Admin("James", "Clark", "fff", "111", "dom@gmail.com");
    }

    @Test
    public void testSavingAdmin() {
        adminService.save(admin);
        verify(adminRepository, times(1)).
                save(any(Admin.class));
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
        adminService.delete(1L);
        verify(adminRepository,times(1))
                .delete(any(Admin.class));
    }
}