package cn.boom.mywebsite.portal.controller;

import cn.boom.mywebsite.content.service.UserService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbUser;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/findOneByEmail")
    @ResponseBody
    public MyWebSiteResult findOneByEmail(String email) {

        TbUser user = userService.findOneByEmail(email);

        if (user == null) {
            return MyWebSiteResult.build(400, "未查询到此用户");
        }

        return MyWebSiteResult.ok(user);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {

        TbUser user = userService.findOneById(id);

        if (user == null) {
            return MyWebSiteResult.build(400, "未查询到此用户");
        }

        return MyWebSiteResult.ok(user);
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public MyWebSiteResult findAll() {
        return MyWebSiteResult.ok(userService.findAll());
    }


    @RequestMapping("/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbUser user) {
        return userService.add(user);
    }

}
