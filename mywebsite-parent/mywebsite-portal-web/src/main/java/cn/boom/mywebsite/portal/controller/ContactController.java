package cn.boom.mywebsite.portal.controller;

import cn.boom.mywebsite.content.service.ContactService;
import cn.boom.mywebsite.content.service.UserService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbContact;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.invoke.MutableCallSite;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Reference
    private ContactService contactService;

    @RequestMapping("/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbContact tbContact) {
        return contactService.add(tbContact);
    }
}
