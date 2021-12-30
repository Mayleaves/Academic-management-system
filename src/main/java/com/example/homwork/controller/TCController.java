package com.example.homwork.controller;

import com.example.homwork.service.TCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TCController {

    @Qualifier("TCImplService")
    @Autowired
    TCService tcService;

    @RequestMapping("/tc/tclist")
    public String list(HttpServletRequest httprequest, Model model){
        HttpSession session=httprequest.getSession();
        if(session.getAttribute("userid")==null){ return "redirect:/login/user";}
        return "/tc/tclist";
    }
}
