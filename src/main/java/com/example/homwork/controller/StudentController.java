package com.example.homwork.controller;

import com.example.homwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/student/slist")
    public String list(HttpServletRequest httprequest){
        HttpSession session=httprequest.getSession();
        if(session.getAttribute("userid")==null){
            //没有登录，需要去登录
            return "redirect:/login/user";
        }
        return "/student/slist";
    }

}
