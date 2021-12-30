package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.Teacher;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import com.example.homwork.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;


@Slf4j
@RestController
@RequestMapping("/webapi/teacher")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/get/{id}")
    public Teacher get(@PathVariable Long id){
        return teacherService.getById(id);
    }

    @GetMapping("/getbypage")
    public R getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                       @RequestParam(value = "tname",defaultValue = "")String tname,
                       @RequestParam(value = "tno",defaultValue = "")String tno){
        Sort sort = Sort.by(Sort.Direction.DESC,"tid");//不可new
        Page<Teacher> teacherPage;
        if(!StringUtils.isEmpty(tname)){
            Teacher teacher=new Teacher();
            teacher.setTname(tname);
            Pageable pageable= PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("tname",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<Teacher> example = Example.of(teacher,matcher);
            teacherPage=teacherService.findAll(example,pageable);
        }else if(!StringUtils.isEmpty(tno)){
            Teacher teacher=new Teacher();
            teacher.setTno(tno);
            Pageable pageable= PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("tno",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<Teacher> example = Example.of(teacher,matcher);
            teacherPage=teacherService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            teacherPage = teacherService.findAll(pageable);
        }
        PageUtils pageUtils=new PageUtils(teacherPage.getContent(),teacherPage.getTotalElements());
        return RUtil.success(pageUtils);
    }

    @PostMapping("/insert")
    public Teacher insert(Teacher teacher){
        return teacherService.insert(teacher);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        teacherService.delete(id);
    }

    @PutMapping("/update")
    public Teacher update(Teacher teacher){
        Teacher oldteacher=teacherService.getById(teacher.getTid());
        if(StringUtils.isEmpty(teacher.getTpassword())){
            teacher.setTpassword(oldteacher.getTpassword());
        }
        return teacherService.update(teacher);
    }

}
