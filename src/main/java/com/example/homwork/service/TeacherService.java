package com.example.homwork.service;

import com.example.homwork.domain.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    Teacher getById(Long id);

    Teacher insert(Teacher teacher);
    void delete(Long id);
    Teacher update(Teacher teacher);

    Page<Teacher> findAll(Pageable pageable);
    Page<Teacher> findAll(Example<Teacher> teacher, Pageable pageable);
    List<Teacher> findByTnoAndTpassword(String tno,String password);
}
