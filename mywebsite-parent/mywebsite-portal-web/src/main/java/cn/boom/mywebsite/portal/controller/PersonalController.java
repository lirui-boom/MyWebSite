package cn.boom.mywebsite.portal.controller;

import cn.boom.mywebsite.content.service.PersonService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbPersonal;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonalController {

    @Reference
    private PersonService personService;

    @RequestMapping("/personal/find")
    @ResponseBody
    public MyWebSiteResult find(){
        TbPersonal personal = personService.findById(1L);
        if (personal == null) {
            return MyWebSiteResult.build(400, "数据异常");
        }
        return MyWebSiteResult.ok(personal);
    }
}
