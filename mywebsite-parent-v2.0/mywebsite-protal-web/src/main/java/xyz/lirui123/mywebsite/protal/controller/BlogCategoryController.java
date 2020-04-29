package xyz.lirui123.mywebsite.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbBlogCategory;
import xyz.lirui123.mywebsite.protal.service.BlogCategoryService;
import xyz.lirui123.mywebsite.response.ResponseResult;


@Controller
@RequestMapping("/blogCategory")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll() {
        return blogCategoryService.findAll();
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {

        if (id == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbBlogCategory blogCategory = blogCategoryService.findOneById(id);

        if (blogCategory == null) {
            return ResponseResult.build(400, "未查询到此分类");
        }

        return ResponseResult.ok(blogCategory);
    }

    @RequestMapping("/findOneByName")
    @ResponseBody
    public ResponseResult findOneByName(String name) {

        if (name == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbBlogCategory blogCategory = blogCategoryService.findOneByName(name);

        if (blogCategory == null) {
            return ResponseResult.build(400, "未查询到此分类");
        }

        return ResponseResult.ok(blogCategory);
    }

}
