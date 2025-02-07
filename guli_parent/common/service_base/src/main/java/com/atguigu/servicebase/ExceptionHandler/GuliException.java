package com.atguigu.servicebase.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   //生成有参构造方法
@NoArgsConstructor    //生成无参构造方法
public class GuliException extends RuntimeException {
    private Integer code;   //状态码
    private String msg;  //异常信息

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
