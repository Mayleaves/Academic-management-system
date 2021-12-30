package com.example.homwork.service.impl;

import com.example.homwork.dao.AdminRepository;
import com.example.homwork.domain.Admin;
import com.example.homwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminImplService implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> findByAuserAndApassword(String auser, String apassword){return adminRepository.findByAuserAndApassword(auser,apassword);}

}
