package com.example.homwork.service;

import com.example.homwork.domain.Course;
import com.example.homwork.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void getById() {
        System.out.println(courseService.getById(1L));

    }

    @Test
    public void insert() {
        Course course = new Course();
        course.setCname("一");
        Course course1 = courseService.insert(course);
        assertNotNull(course1.getCid());
    }

    @Test
    void update() {
        Course course = courseService.getById(4L);
        course.setCname("三");
        courseService.update(course);
    }
}