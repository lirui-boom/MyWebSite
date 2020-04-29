package xyz.lirui123.mywebsite.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbContact;
import xyz.lirui123.mywebsite.protal.service.ContactService;
import xyz.lirui123.mywebsite.response.ResponseResult;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestBody TbContact tbContact) {
        return contactService.add(tbContact);
    }
}
