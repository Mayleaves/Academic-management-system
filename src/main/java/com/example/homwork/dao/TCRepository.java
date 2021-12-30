package com.example.homwork.dao;

import com.example.homwork.domain.TC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCRepository extends JpaRepository<TC,Long> {
    Page<TC> findByTno(String tno, Pageable pageable);
}
