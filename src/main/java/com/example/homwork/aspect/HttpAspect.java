package com.example.homwork.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class HttpAspect {
    @Before("execution(public * com.example.homwork.service.StudentService.*(..))")
    public void log(){
        System.out.println("HttpAspect执行了！");
    }
}
