package com.example.homwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tc")
@Data
public class TC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tcid;
    private String tno;
    private String tname;
    private String cname;
    private String eva;
}
