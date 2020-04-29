package xyz.lirui123.mywebsite.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "/admin/index.html";
    }
}
