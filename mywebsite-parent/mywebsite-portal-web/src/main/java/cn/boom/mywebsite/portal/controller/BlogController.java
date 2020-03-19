package cn.boom.mywebsite.portal.controller;

import cn.boom.mywebsite.content.service.BlogService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbBlog;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Reference
    private BlogService blogService;

    @RequestMapping("/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbBlog tbBlog, int page, int rows) {
        return blogService.search(tbBlog, page, rows);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {

        if (id == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        TbBlog tbBlog = blogService.findOne(id);

        if (tbBlog == null) {
            return MyWebSiteResult.build(400, "未查询到此文章");
        }

        return MyWebSiteResult.ok(tbBlog);
    }

    @RequestMapping("/update")
    @ResponseBody
    public MyWebSiteResult update(@RequestBody TbBlog tbBlog) {
        return blogService.update(tbBlog);
    }


}
