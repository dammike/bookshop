package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Admin;
import com.dammike.bookstore.graemelee.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    public void delete(Long id) {
        Admin admin = adminRepository.findById(id).get();
        adminRepository.delete(admin);
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    public Admin getAdminById(Long id) {
        Admin admin = adminRepository.findById(id).get();
        return admin;
    }

    public Admin getAdminByEmail(String email) {
        Optional<Admin> optional = adminRepository.findByEmailContaining(email);
        return optional.orElseThrow();
    }
}
