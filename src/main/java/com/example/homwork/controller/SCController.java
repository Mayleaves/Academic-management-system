package com.example.homwork.controller;

import com.example.homwork.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SCController {

    @Qualifier("SCImplService")
    @Autowired
    SCService scService;

    @RequestMapping("/sc/sclist")
    public String list(HttpServletRequest httprequest){
        HttpSession session=httprequest.getSession();
        if(session.getAttribute("userid")==null){ return "redirect:/login/user";}
        return "/sc/sclist";
    }
}
