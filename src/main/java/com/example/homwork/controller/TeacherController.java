package com.example.homwork.controller;

import com.example.homwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/teacher/tlist")
    public String list(HttpServletRequest httprequest){
        HttpSession session=httprequest.getSession();
        if(session.getAttribute("userid")==null){ return "redirect:/login/user";}
        return "/teacher/tlist";
    }
}
