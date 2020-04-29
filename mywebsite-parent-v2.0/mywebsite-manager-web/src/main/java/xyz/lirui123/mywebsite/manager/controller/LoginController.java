package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "redirect:/admin/index.html";
    }
}
