package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.SC;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import com.example.homwork.service.SCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;


@Slf4j
@RestController
@RequestMapping("/webapi/sc")
public class SCRestController {

    @Qualifier("SCImplService")
    @Autowired
    private SCService scService;

    @GetMapping("/get/{id}")
    public SC get(@PathVariable Long id){ return scService.getById(id);}

    @GetMapping("/getbypage")
    public R getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                       @RequestParam(value = "cname",defaultValue = "")String cname,
                       @RequestParam(value = "sno",defaultValue = "")String sno){
        Sort sort = Sort.by(Sort.Direction.DESC,"scid");
        Page<SC> scPage;
        if(!StringUtils.isEmpty(cname)){
            SC sc=new SC();
            sc.setCname(cname);
            sc.setSno(sno);
            Pageable pageable=PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("cname",
                    ExampleMatcher.GenericPropertyMatchers.contains());
            Example<SC> example = Example.of(sc,matcher);
            scPage=scService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            scPage = scService.findBySno(sno,pageable);
        }
        PageUtils pageUtils=new PageUtils(scPage.getContent(),scPage.getTotalElements());
        return RUtil.success(pageUtils);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){scService.delete(id);}

}
