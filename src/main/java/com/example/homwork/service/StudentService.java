package com.example.homwork.service;

import com.example.homwork.domain.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    Student getById(Long sid);//@GetMapping("/get/{sid}")

    Student insert(Student student);
    void delete(Long id);
    Student update(Student student);

    Page<Student> findAll(Pageable pageable);//@GetMapping("/getbypage")
    Page<Student> findAll(Example<Student> student,Pageable pageable);//@GetMapping("/getbypage")
    List<Student> findBySnoAndSpassword(String sno,String password);

}