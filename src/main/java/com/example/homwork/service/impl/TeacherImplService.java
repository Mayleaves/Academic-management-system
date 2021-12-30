package com.example.homwork.service.impl;

import com.example.homwork.dao.TeacherRepository;
import com.example.homwork.domain.Teacher;
import com.example.homwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherImplService implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher getById(Long id){return teacherRepository.findById(id).orElse(null);}

    public Teacher insert(Teacher teacher) {return teacherRepository.save(teacher);}
    public void delete(Long id){
        Teacher teacher=new Teacher();
        teacher.setTid(id);
        teacherRepository.delete(teacher);
    }
    public Teacher update(Teacher teacher) {return teacherRepository.save(teacher);}

    public Page<Teacher> findAll(Pageable pageable){return teacherRepository.findAll(pageable);}
    public Page<Teacher> findAll(Example<Teacher> teacher, Pageable pageable){return teacherRepository.findAll(teacher,pageable);}
    public List<Teacher> findByTnoAndTpassword(String tno,String password){return teacherRepository.findByTnoAndTpassword(tno,password);}

}
