package com.example.homwork.service.impl;

import com.example.homwork.dao.SCRepository;
import com.example.homwork.domain.SC;
import com.example.homwork.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SCImplService implements SCService {

    @Autowired
    SCRepository scRepository;

    public SC getById(Long id){return scRepository.findById(id).orElse(null);}

    public void delete(Long id){
        SC sc=new SC();
        sc.setScid(id);
        scRepository.delete(sc);
    };

    public Page<SC> findAll(Pageable pageable){return scRepository.findAll(pageable);}
    public Page<SC> findAll(Example<SC> sc, Pageable pageable){return scRepository.findAll(sc,pageable);}

    public Page<SC> findBySno(String sno,Pageable pageable){return scRepository.findBySno(sno,pageable);};

}
