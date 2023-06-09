package com.lantu.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{

    private Integer code;
    private  String meesage;
    private  T date;

    public static <T> Result<T>  success(){
        return  new Result<>(20000,"success",null);
    }
    public static <T> Result<T>  success(T data){
        return  new Result<>(20000,"success",data);
    }
    public static <T> Result<T>  success(T data, String messsage){
        return  new Result<>(20001,messsage,data);

    }
    public static <T> Result<T>  success(String meesage){
        return  new Result<>(20000,meesage,null);
    }
    public static <T> Result<T>  fail(){
        return  new Result<>(20001,"fail",null);

    }
    public static <T> Result<T>  fail(Integer code){
        return  new Result<>(code,"fail",null);

    }
    public static <T> Result<T>  fail(Integer code, String  meesage){
        return  new Result<>(code,meesage,null);

    }
    public static <T> Result<T>  fail(String message){
        return  new Result<>(20001,message,null);

    }
}
