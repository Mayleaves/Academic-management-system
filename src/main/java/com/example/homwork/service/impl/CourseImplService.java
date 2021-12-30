package com.example.homwork.service.impl;

import com.example.homwork.dao.CourseRepository;
import com.example.homwork.domain.Course;
import com.example.homwork.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CourseImplService implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course getById(Long id){return courseRepository.findById(id).orElse(null);}

    public Course insert(Course course){return courseRepository.save(course);};
    public void delete(Long id){
        Course course=new Course();
        course.setCid(id);
        courseRepository.delete(course);
    };
    public Course update(Course course){return courseRepository.save(course);};

    public Page<Course> findAll(Pageable pageable){return courseRepository.findAll(pageable);}
    public Page<Course> findAll(Example<Course> course, Pageable pageable){return courseRepository.findAll(course,pageable);}

}