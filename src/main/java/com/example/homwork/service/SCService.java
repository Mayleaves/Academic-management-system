package com.example.homwork.service;

import com.example.homwork.domain.SC;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface SCService {

    SC getById(Long id);

    void delete(Long id);

    Page<SC> findAll(Pageable pageable);
    Page<SC> findAll(Example<SC> sc, Pageable pageable);

    Page<SC> findBySno(String sno,Pageable pageable);//@GetMapping("/getbypage")

}
