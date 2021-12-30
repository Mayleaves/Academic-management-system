package com.example.homwork.webapi;

import com.example.homwork.domain.Admin;
import com.example.homwork.domain.Student;
import com.example.homwork.domain.Teacher;
import com.example.homwork.service.AdminService;
import com.example.homwork.service.StudentService;
import com.example.homwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/webapi/login")
public class LoginRestController {
    //每个都必须要有@Autowired
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    AdminService adminService;

    @GetMapping("/user")
    public int user(HttpServletRequest httprequest, String username, String password, int flag){
        if (flag==1){//学生
            //判断用户名和密码是否存在
            List<Student> students=studentService.findBySnoAndSpassword(username, password);
            if(students.size()>0){
                //如果存在，写入session
                HttpSession session=httprequest.getSession();
                session.setAttribute("userid",students.get(0).getSid());
                session.setAttribute("user",students.get(0));
                return 1;//成功
            }else{
                //如果不存在，返回出错
                return -1;//不存在用户
            }
        }else if(flag==2){//教师
            List<Teacher> teachers=teacherService.findByTnoAndTpassword(username, password);
            if(teachers.size()>0){
                HttpSession session=httprequest.getSession();
                session.setAttribute("userid",teachers.get(0).getTid());
                session.setAttribute("user",teachers.get(0));
                return 2;
            }else{
                return -1;
            }
        }else {//管理员
            List<Admin> admins=adminService.findByAuserAndApassword(username, password);
            if(admins.size()>0){
                HttpSession session=httprequest.getSession();
                session.setAttribute("userid",admins.get(0).getAid());
                session.setAttribute("user",admins.get(0));
                return 3;
            }else{
                return -1;
            }
        }
    }
}
