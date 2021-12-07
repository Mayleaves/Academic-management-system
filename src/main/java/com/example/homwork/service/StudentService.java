package com.example.homwork.service;

import com.example.homwork.domain.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    Student getById(Long id);
    List<Student> findAll();
    Page<Student> findAll(Pageable pageable);
    Page<Student> findAll(Example<Student> student,Pageable pageable);

    Student insert(Student student);
    void delete(Student student);
    void delete(Long id);
    Student update(Student student);
    List<Student> findByNameLike(String name);
    List<Student> findByName(String name);

    Optional<Student> find(Long id);

}