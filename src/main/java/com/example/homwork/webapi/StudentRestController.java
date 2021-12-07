package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.Student;
import com.example.homwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/webapi/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get/{id}")
    public Student get(@PathVariable Long id){
        Student student=studentService.getById(id);
        return student;
    }

    @GetMapping("/getbypage")
    public PageUtils getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                               @RequestParam(value = "size",defaultValue = "10")Integer size,
                               @RequestParam(value = "name",defaultValue = "")String name,
                               @RequestParam(value = "number",defaultValue = "")String number){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");//不可new
        //Pageable pageable = PageRequest.of(page,size,sort);//接口
        Page<Student> studentPage;//=studentService.findAll(pageable);
        if(!StringUtils.isEmpty(name)){
            Student student=new Student();
            student.setName(name);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("name",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<Student> example = Example.of(student,matcher);
            studentPage=studentService.findAll(example,pageable);
        }else if(!StringUtils.isEmpty(number)){
            Student student=new Student();
            student.setNumber(number);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("number",
                    ExampleMatcher.GenericPropertyMatchers.exact());//精确查找
            Example<Student> example = Example.of(student,matcher);
            studentPage=studentService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            studentPage = studentService.findAll(pageable);
        }
//        if(org.apache.commons.lang.StringUtils.isEmpty(name)){
//            Pageable pageable = PageRequest.of(page,size,sort);
//            studentPage = studentService.findAll(pageable);
//        }else{
//            Student student=new Student();
//            student.setName(name);
//            Pageable pageable=PageRequest.of(0,10,sort);
//            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("name",
//                    ExampleMatcher.GenericPropertyMatchers.contains());
//            Example<Student> example = Example.of(student,matcher);
//            studentPage=studentService.findAll(example,pageable);
//        }
        PageUtils pageUtils=new PageUtils(studentPage.getContent(),studentPage.getTotalElements());
        return pageUtils;
    }

    @GetMapping("/list")
    public List<Student> findByName(String name){
        List<Student> all;
        //org.apache.commons.lang.StringUtils.isEmpty(name)//不需要导包
        if(StringUtils.isEmpty(name)){//查询全部
            all=studentService.findAll();
        }else{//模糊查找：按姓名
            all=studentService.findByNameLike(name);
        }
        return all;
    }

    @PostMapping("/insert")
    public Student insert(Student student){
        student=studentService.insert(student);
        return student;
    }

    @DeleteMapping("/delete/{id}")//利用主键（id）即可
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

    @PutMapping("/update")//修改时必须带上主键（id）
    public Student update(Student student){
        Student oldstudent=studentService.getById(student.getId());//读取旧的数据
        if(org.apache.commons.lang.StringUtils.isEmpty(student.getPassword())){//为什么不能导包啊？
            student.setPassword(oldstudent.getPassword());//如果没有新密码，则保存原来的密码
        }
        student=studentService.update(student);
        return student;
    }

}
