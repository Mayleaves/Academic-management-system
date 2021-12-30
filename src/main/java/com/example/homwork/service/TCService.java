package com.example.homwork.service;

import com.example.homwork.domain.TC;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TCService {

    TC getById(Long id);

    void delete(Long id);

    Page<TC> findAll(Pageable pageable);
    Page<TC> findAll(Example<TC> tc, Pageable pageable);

    Page<TC> findByTno(String tno,Pageable pageable);
}
