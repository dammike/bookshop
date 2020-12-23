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

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (!admin.isPresent()) {
            try {
                throw new Exception("No such admin by the specified ID: " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        adminRepository.delete(admin.get());
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> result = adminRepository.findById(id);
        if (!result.isPresent()) {
            try {
                throw new Exception("No such admin by the specified ID: " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.get();
    }

    public Admin getAdminByEmail(String email) {
        Optional<Admin> result = adminRepository.findByEmailContaining(email);
        if (!result.isPresent()) {
            try {
                throw new Exception("No such admin by the specified email: " + email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.get();
    }
}
