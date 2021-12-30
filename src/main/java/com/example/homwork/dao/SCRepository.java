package com.example.homwork.dao;

import com.example.homwork.domain.SC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SCRepository extends JpaRepository<SC,Long> {
    Page<SC> findBySno(String sno, Pageable pageable);
}
