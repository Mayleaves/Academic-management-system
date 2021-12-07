package com.example.homwork.controller;

import com.example.homwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;
    @RequestMapping("/student/list")
    public String list(Model model,String name){
        //log.info("name:{}",name);
        /*if(name==null||name.equals("")){
            List<Student> student=studentService.findAll();//全部
            model.addAttribute("data",student);
        }else{
            List<Student> student=studentService.findByNameLike(name);//模糊查找
            model.addAttribute("data",student);
        }*/
        return "/student/list";
    }



    /*
    @ResponseBody
    @GetMapping("/student/search")
    //@RequestMapping("/findByName")
    public List<Student> findByName(String name){
        return studentService.findByName(name);
    }

    /*
    @RequestMapping("/hello")
    public String sayHi()
    {
        return  "hello,spring Boot!";
    }

    @RequestMapping("/student/findall")
    public List<Student> findAll()
    {
        return studentService.findAll();
    }

    @RequestMapping("/student/find")
    public Optional<Student> find() {return studentService.find(1L);}
    */
}
