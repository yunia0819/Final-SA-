package com.example.final_project.entity;

import org.springframework.http.HttpStatus;

public class ReponseMessage <T>{
    private  Integer code;
    private  String message;
    private  T data;

    public ReponseMessage (Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    //街口請求成功
    public static <T> ReponseMessage success (T data){
        return new ReponseMessage (HttpStatus.OK.value(), "success!!", data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
