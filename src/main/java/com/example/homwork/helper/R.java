package com.example.homwork.helper;

import lombok.Data;

@Data
public class R<T> {
    private Integer code;//错误代码
    private String msg;//提示信息
    private T data;//具体内容
}
