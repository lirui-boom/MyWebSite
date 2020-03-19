package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.content.service.ContactService;
import cn.boom.mywebsite.content.service.UserService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbContact;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Reference
    private ContactService contactService;

    @RequestMapping("/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbContact tbContact, int page, int rows) {
        return contactService.search(tbContact, page, rows);
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {
        return MyWebSiteResult.ok(contactService.findOneById(id));
    }

    @RequestMapping("/reply")
    @ResponseBody
    public MyWebSiteResult reply(@RequestBody TbContact tbContact) {
        return contactService.reply(tbContact);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public MyWebSiteResult deleteIds(Long[] ids) {
        return contactService.deleteIds(ids);
    }
}
