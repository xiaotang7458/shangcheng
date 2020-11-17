package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.ExceptionHandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-15
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description = "讲师模块")
public class EduTeacherController {
      @Autowired
   private EduTeacherService eduTeacherService;

    /**
     * 查询全部
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("所有列表")
    public Result findAll(){
          List<EduTeacher> list = eduTeacherService.list(null);
          return Result.successed().data("items",list);
      }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据ID删除")
    public Result delete(@ApiParam(name = "id",value = "讲师id",required = true/**此参数必须存在*/) @PathVariable("id") String id){
          boolean b = eduTeacherService.removeById(id);
          if (b == true){
              return Result.successed();
          }else {
              return Result.failed();
          }
      }

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/page/{page}/{limit}")
    @ApiOperation("分页查询")
    public Result page(@ApiParam(name = "page",value = "当前页数",required = true)@PathVariable("page") Long page,
                       @ApiParam(name="limit",value = "每页显示数",required = true)@PathVariable("limit") Long limit){
        Page<EduTeacher> page1=new Page<>(page,limit);
        IPage<EduTeacher> iPage = eduTeacherService.page(page1, null);
        long total = iPage.getTotal();  //获取总页数
        List<EduTeacher> records = iPage.getRecords();  //数据的list集合
      return Result.successed().data("total",total).data("records",records);
    }

    @PostMapping("/pageCollection/{page}/{limit}")
    @ApiOperation("条件分页查询")
    public Result pageCollection(@ApiParam(name = "page",value = "当前页数",required = true)@PathVariable("page") Long page,
                                 @ApiParam(name="limit",value = "每页显示数",required = true)@PathVariable("limit") Long limit,
                                 @RequestBody(required = false/**此参数非必须存在*/) TeacherQuery teacherQuery){
  //@RequestBody:使用json传递数据，把json数据封装到对应的对象里面----需要POST提交方式
  //@ResponseBody:用来返回JSON数据或者是XML数据,不会再走视图处理器
        //构造查询条件
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if(!StringUtils.isEmpty(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());   //大于等于
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_modified",teacherQuery.getEnd());   //小于等于
        }
        Page<EduTeacher> paged=new Page<>(page,limit);
        IPage<EduTeacher> iPage = eduTeacherService.page(paged, wrapper);
        long total = iPage.getTotal();  //获取总页数
        List<EduTeacher> records = iPage.getRecords();  //数据的list集合
        return Result.successed().data("total",total).data("records",records);
    }

    /**
     * 添加讲师
     * @param eduTeacher
     * @return
     */
    @PostMapping("/addTeacher")
    @ApiOperation("添加讲师")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.save(eduTeacher);
        return b?Result.successed():Result.failed();
    }

    /**
     * 修改讲师
     * @param eduTeacher
     * @return
     */
    @PostMapping("/updateTeacher")
    @ApiOperation("修改讲师")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
//        try{
//            int i=10/0;
//        }catch (Exception e){
//           throw new GuliException(250,"执行了自定义异常")
//        }
        return b?Result.successed():Result.failed();

    }

}

