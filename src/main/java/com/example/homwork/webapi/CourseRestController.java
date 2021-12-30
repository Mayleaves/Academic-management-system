package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.Course;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import com.example.homwork.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Slf4j
@RestController
@RequestMapping("/webapi/course")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get/{id}")
    public Course get(@PathVariable Long id){
        return courseService.getById(id);
    }

    @GetMapping("/getbypage")
    public R getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                       @RequestParam(value = "cname",defaultValue = "")String cname,
                       @RequestParam(value = "cno",defaultValue = "")String cno){
        Sort sort = Sort.by(Sort.Direction.DESC,"cid");
        Page<Course> coursePage;
        if(!StringUtils.isEmpty(cname)){
            Course course=new Course();
            course.setCname(cname);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("cname",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<Course> example = Example.of(course,matcher);
            coursePage=courseService.findAll(example,pageable);
        }else if(!StringUtils.isEmpty(cno)){
            Course course=new Course();
            course.setCno(cno);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("cno",
                    ExampleMatcher.GenericPropertyMatchers.exact());//精确查找
            Example<Course> example = Example.of(course,matcher);
            coursePage=courseService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            coursePage = courseService.findAll(pageable);
        }
        PageUtils pageUtils=new PageUtils(coursePage.getContent(),coursePage.getTotalElements());
        return RUtil.success(pageUtils);
    }

    @PostMapping("/insert")
    public Course insert(Course course){return courseService.insert(course);}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){courseService.delete(id);}

    @PutMapping("/update")
    public Course update(Course course){return courseService.update(course);}

}
