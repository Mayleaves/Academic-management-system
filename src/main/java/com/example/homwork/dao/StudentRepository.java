package com.example.homwork.dao;

import com.example.homwork.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("from tb_student o where o.name LIKE CONCAT('%',:name,'%')")//利用CONCAT('%',:name,'%')实现模糊查找
    List<Student> findByNameLike(@Param("name") String name);

    @Query("from tb_student o where o.name = :name")//精准查询
    List<Student> findByName(@Param("name") String name);

}
