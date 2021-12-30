package com.example.homwork.helper;

public enum REnum {
    UNKOWN_ERR(-999,"未知错误"),
    COMMON_ERR(-10,"一般性错误"),
    LOGIN_ERR(-2,"出错了，不正确的用户名或密码！"),
    SUCCESS_ERR(1,"成功");

    private Integer code;
    private String msg;

    REnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
