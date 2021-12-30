package com.example.homwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sc")
@Data
public class SC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scid;
    private String sno;
    private String sname;
    private String cname;
    private Integer usualScore;//用驼峰：表内要是usual_score
    private Integer finalScore;
    private Integer makeupScore;
    //private Integer usual_score;//【錯誤】不要乱加下划线，下划线会转大写。
    //private Integer final_score;//【錯誤】不能表和domain都用驼峰。
    //private Integer makeup_score;
}
