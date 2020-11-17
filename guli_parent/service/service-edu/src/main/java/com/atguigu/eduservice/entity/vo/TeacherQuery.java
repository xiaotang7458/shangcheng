package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件的封装
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2019-12-10 15:25:25")
    private String begin;  //使用String类型，前端传来的参数无需进行类型转换
    @ApiModelProperty(value = "查询结束时间",example = "2019-12-10 15:25:25")
    private String end;

}
