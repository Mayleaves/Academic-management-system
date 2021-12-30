package com.example.homwork.service;

import com.example.homwork.domain.Course;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface CourseService {


    Course getById(Long id);

    Course insert(Course course);
    void delete(Long id);
    Course update(Course course);

    Page<Course> findAll(Pageable pageable);
    Page<Course> findAll(Example<Course> course, Pageable pageable);

}