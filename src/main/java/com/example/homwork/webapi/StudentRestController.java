package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.Student;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import com.example.homwork.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.regex.Pattern;///

@Slf4j
@RestController
@RequestMapping("/webapi/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get/{sid}")//editStudent
    public Student get(@PathVariable Long sid){
        return studentService.getById(sid);
    }

    @GetMapping("/getbypage")//loadTable()+查询功能
    public R getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                       @RequestParam(value = "sname",defaultValue = "")String sname,
                       @RequestParam(value = "sno",defaultValue = "")String sno){
        Sort sort = Sort.by(Sort.Direction.DESC,"sid");//不可new
        Page<Student> studentPage;
        if(!StringUtils.isEmpty(sname)){
            Student student=new Student();
            student.setSname(sname);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("sname",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<Student> example = Example.of(student,matcher);
            studentPage=studentService.findAll(example,pageable);
        }else if(!StringUtils.isEmpty(sno)){
            Student student=new Student();
            student.setSno(sno);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("sno",
                    ExampleMatcher.GenericPropertyMatchers.exact());//精确查找
            Example<Student> example = Example.of(student,matcher);
            studentPage=studentService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            studentPage = studentService.findAll(pageable);
        }
        PageUtils pageUtils=new PageUtils(studentPage.getContent(),studentPage.getTotalElements());
        return RUtil.success(pageUtils);
    }

    @PostMapping("/insert")
    public Student insert(Student student){
        return studentService.insert(student);
    }

    @DeleteMapping("/delete/{id}")//利用主键（id）即可
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

    @PutMapping("/update")//修改时必须带上主键（id）
    public Student update(Student student){
        Student oldstudent=studentService.getById(student.getSid());//读取旧的数据
        if(StringUtils.isEmpty(student.getSpassword())){
            student.setSpassword(oldstudent.getSpassword());//如果没有新密码，则保存原来的密码
        }
        return studentService.update(student);
    }

}
