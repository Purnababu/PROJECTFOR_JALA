package com.example.ProjectJala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectJala.entity.Admin;
import com.example.ProjectJala.repo.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200") 
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PostMapping("/login")
    public Admin loginAdmin(@RequestBody Admin admin) {
        return adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
    }
}
