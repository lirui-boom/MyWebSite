package xyz.lirui123.mywebsite.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbUser;
import xyz.lirui123.mywebsite.protal.service.UserService;
import xyz.lirui123.mywebsite.response.ResponseResult;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findOneByEmail")
    @ResponseBody
    public ResponseResult findOneByEmail(String email) {

        TbUser user = userService.findOneByEmail(email);

        if (user == null) {
            return ResponseResult.build(400, "未查询到此用户");
        }

        return ResponseResult.ok(user);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {

        TbUser user = userService.findOneById(id);

        if (user == null) {
            return ResponseResult.build(400, "未查询到此用户");
        }

        return ResponseResult.ok(user);
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll() {
        return ResponseResult.ok(userService.findAll());
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestBody TbUser user) {
        return userService.add(user);
    }

}
