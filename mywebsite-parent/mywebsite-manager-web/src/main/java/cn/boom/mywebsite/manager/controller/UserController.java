package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.content.service.UserService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbUser;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {
        return MyWebSiteResult.ok(userService.findOneById(id));
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public MyWebSiteResult findAll() {
        return MyWebSiteResult.ok(userService.findAll());
    }

    @RequestMapping("/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.search(user, page, rows);
    }

    @RequestMapping("/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbUser user) {
        return userService.add(user);
    }


    @RequestMapping("/update")
    @ResponseBody
    public MyWebSiteResult update(@RequestBody TbUser user) {
        return userService.update(user);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public MyWebSiteResult deleteIds(Long[] ids) {
        return userService.deleteIds(ids);
    }

}
