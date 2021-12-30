package com.example.homwork.aspect;

import com.example.homwork.exception.RException;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理，使用ControllerAdvice(增强控制器)
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e){
        if(e instanceof RException){
            RException rException = (RException) e;
            return RUtil.error(rException.getCode(),rException.getMessage());
        }
        return RUtil.error(-999,e.getMessage());
    }
}
