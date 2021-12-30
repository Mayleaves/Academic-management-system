package com.example.homwork.dao;

import com.example.homwork.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findBySnoAndSpassword(String sno,String spassword);
}
