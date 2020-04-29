package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbUser;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(userService.findOneById(id));
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll() {
        return ResponseResult.ok(userService.findAll());
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.search(user, page, rows);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestBody TbUser user) {
        return userService.add(user);
    }


    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(@RequestBody TbUser user) {
        return userService.update(user);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public ResponseResult deleteIds(Long[] ids) {
        return userService.deleteIds(ids);
    }

}
