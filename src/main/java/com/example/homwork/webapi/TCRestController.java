package com.example.homwork.webapi;

import com.example.homwork.core.PageUtils;
import com.example.homwork.domain.TC;
import com.example.homwork.helper.R;
import com.example.homwork.helper.RUtil;
import com.example.homwork.service.TCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Slf4j
@RestController
@RequestMapping("/webapi/tc")
public class TCRestController {

    @Qualifier("TCImplService")
    @Autowired
    private TCService tcService;

    @GetMapping("/get/{id}")
    public TC get(@PathVariable Long id){ return tcService.getById(id);}

    @GetMapping("/getbypage")
    public R getByPage(@RequestParam(value = "page",defaultValue = "0")Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                       @RequestParam(value = "cname",defaultValue = "")String cname,
                       @RequestParam(value = "tno",defaultValue = "")String tno){
        Sort sort = Sort.by(Sort.Direction.DESC,"tcid");
        Page<TC> tcPage;
        if(!StringUtils.isEmpty(cname)){
            TC tc=new TC();
            tc.setCname(cname);
            tc.setTno(tno);
            Pageable pageable= PageRequest.of(0,10,sort);
            ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("cname",
                    ExampleMatcher.GenericPropertyMatchers.contains());//模糊查询
            Example<TC> example = Example.of(tc,matcher);
            tcPage=tcService.findAll(example,pageable);
        }else{
            Pageable pageable = PageRequest.of(page,size,sort);
            tcPage = tcService.findByTno(tno,pageable);
        }
        PageUtils pageUtils=new PageUtils(tcPage.getContent(),tcPage.getTotalElements());
        return RUtil.success(pageUtils);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){tcService.delete(id);}
}
