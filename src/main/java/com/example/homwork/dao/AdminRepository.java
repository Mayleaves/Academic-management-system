package com.example.homwork.dao;

import com.example.homwork.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    List<Admin> findByAuserAndApassword(String name, String password);
}

