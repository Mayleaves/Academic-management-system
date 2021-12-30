package com.example.homwork.service;

import com.example.homwork.domain.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<Admin> findByAuserAndApassword(String auser, String apassword);
}
