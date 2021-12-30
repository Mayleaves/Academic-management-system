package com.example.homwork.service.impl;

import com.example.homwork.dao.TCRepository;
import com.example.homwork.domain.TC;
import com.example.homwork.service.TCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TCImplService implements TCService {

    @Autowired
    TCRepository tcRepository;

    public TC getById(Long id){ return tcRepository.findById(id).orElse(null);}

    public void delete(Long id){
        TC tc=new TC();
        tc.setTcid(id);
        tcRepository.delete(tc);
    };

    public Page<TC> findAll(Pageable pageable){return tcRepository.findAll(pageable);}
    public Page<TC> findAll(Example<TC> tc, Pageable pageable){return tcRepository.findAll(tc,pageable);}

    public Page<TC> findByTno(String tno,Pageable pageable){return tcRepository.findByTno(tno,pageable);};
}
