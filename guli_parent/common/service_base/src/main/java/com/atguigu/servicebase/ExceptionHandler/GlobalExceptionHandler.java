package com.atguigu.servicebase.ExceptionHandler;

import com.atguigu.commonutils.Result;
import com.atguigu.commonutils.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
//可实现1、全局异常处理;2、全局数据绑定;3、全局数据预处理
//在此为全局异常处理
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.failed().message("执行了全局异常");
    }

  //自定义异常
  @ExceptionHandler(GuliException.class)
  @ResponseBody //为了返回数据
  public Result error(GuliException e){
      e.printStackTrace();
      log.error(ExceptionUtil.getMessage(e));
      return Result.failed().code(e.getCode()).message(e.getMsg());
  }
}
