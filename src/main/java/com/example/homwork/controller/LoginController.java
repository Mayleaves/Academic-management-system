package com.example.homwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login/user")
    public String login(){
        return "/login/user";
    }
}
