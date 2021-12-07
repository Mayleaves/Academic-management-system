package com.example.homwork.service.impl;

import com.example.homwork.dao.StudentRepository;
import com.example.homwork.domain.Student;
import com.example.homwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentImplService implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getById(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    public List<Student> findAll() {return studentRepository.findAll();}
    public Page<Student> findAll(Pageable pageable){return studentRepository.findAll(pageable);}
    public Page<Student> findAll(Example<Student> student,Pageable pageable){return studentRepository.findAll(student,pageable);}

    public Student insert(Student student) {return studentRepository.save(student);}
    public void delete(Student student){studentRepository.delete(student);}
    public void delete(Long id){
        Student student=new Student();
        student.setId(id);
        studentRepository.delete(student);
    }
    public Student update(Student student) {return studentRepository.save(student);}
    public List<Student> findByNameLike(String name) {return studentRepository.findByNameLike(name);}
    public List<Student> findByName(String name) {return studentRepository.findByName(name);}

    public Optional<Student> find(Long id){ return studentRepository.findById(id);}
}