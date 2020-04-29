package xyz.lirui123.mywebsite.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbBlog;
import xyz.lirui123.mywebsite.protal.service.BlogService;
import xyz.lirui123.mywebsite.response.ResponseResult;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody TbBlog tbBlog, int page, int rows) {
        return blogService.search(tbBlog, page, rows);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {

        if (id == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbBlog tbBlog = blogService.findOne(id);

        if (tbBlog == null) {
            return ResponseResult.build(400, "未查询到此文章");
        }

        return ResponseResult.ok(tbBlog);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(@RequestBody TbBlog tbBlog) {
        return blogService.update(tbBlog);
    }


}
