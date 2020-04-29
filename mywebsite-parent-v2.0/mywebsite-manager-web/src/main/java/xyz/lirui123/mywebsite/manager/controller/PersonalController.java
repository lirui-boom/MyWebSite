package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbPersonal;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.PersonService;

@Controller
public class PersonalController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/personal/find")
    @ResponseBody
    public ResponseResult find(){
        TbPersonal personal = personService.findById(1L);
        if (personal == null) {
            return ResponseResult.build(400, "数据异常");
        }
        return ResponseResult.ok(personal);
    }

    @RequestMapping("/personal/update")
    @ResponseBody
    public ResponseResult update(@RequestBody TbPersonal tbPersonal){
        return personService.update(tbPersonal);
    }
}
