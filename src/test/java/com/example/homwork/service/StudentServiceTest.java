package com.example.homwork.service;

import com.example.homwork.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void findAll() {
        List<Student> studentList=studentService.findAll();
        System.out.println(studentList);
        assertNotNull(studentList);
    }

    @Test
    public void filterByName() {
        Student student=new Student();
        student.setName("夏");
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(0,10,sort);
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Student> example = Example.of(student,matcher);
        //Example<Student> example = Example.of(student);
        Page<Student> studentPage=studentService.findAll(example,pageable);
        System.out.println(studentPage);
    }

    @Test
    public void findByName() {
        List<Student> studentList = studentService.findByName("夏星月");
        System.out.println(studentList);
        assertNotNull(studentList);
    }

    @Test
    public void insert() {
        Student student = new Student();
        student.setName("李四一");
        student.setNumber("2019112122");
        student.setSex(1);
        student.setAge(27);
        student.setScore(60);
        Student student1 = studentService.insert(student);
        assertNotNull(student1.getId());
    }
    @Test
    public void delete() {
        Student student = studentService.getById(2L);
        studentService.delete(student);
    }
    @Test
    public void update() {
        Student student = studentService.getById(2L);
        student.setName("张三");
        student.setNumber("2019112127");
        student.setSex(1);
        student.setAge(27);
        student.setScore(60);
        student.setPassword("123");
        studentService.update(student);
    }
    @Test
    public void find() {
        studentService.find(2019131132L);
    }

}