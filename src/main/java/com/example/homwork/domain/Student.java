package com.example.homwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    private String sname;
    private String sno;
    private Integer ssex;
    private Integer sage;
    private Float sscore;
    private String spassword;
}
