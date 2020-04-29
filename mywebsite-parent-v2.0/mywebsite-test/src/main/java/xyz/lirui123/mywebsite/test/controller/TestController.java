package xyz.lirui123.mywebsite.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lirui123.mywebsite.response.ResponseResult;

@RestController
public class TestController {

    @RequestMapping("/test")
    public ResponseResult test() {
        return ResponseResult.ok();
    }
}
