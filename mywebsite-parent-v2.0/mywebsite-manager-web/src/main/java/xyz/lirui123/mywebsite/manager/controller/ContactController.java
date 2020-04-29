package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbContact;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.ContactService;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody TbContact tbContact, int page, int rows) {
        return contactService.search(tbContact, page, rows);
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(contactService.findOneById(id));
    }

    @RequestMapping("/reply")
    @ResponseBody
    public ResponseResult reply(@RequestBody TbContact tbContact) {
        return contactService.reply(tbContact);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public ResponseResult deleteIds(Long[] ids) {
        return contactService.deleteIds(ids);
    }
}
