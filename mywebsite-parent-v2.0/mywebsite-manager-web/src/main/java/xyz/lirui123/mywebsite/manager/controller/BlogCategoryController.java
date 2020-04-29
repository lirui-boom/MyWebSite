package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbBlogCategory;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.BlogCategoryService;


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

    @RequestMapping("add")
    @ResponseBody
    public ResponseResult add(@RequestBody TbBlogCategory tbBlogCategory) {
        return blogCategoryService.add(tbBlogCategory);
    }

    @RequestMapping("update")
    @ResponseBody
    public ResponseResult update(@RequestBody TbBlogCategory tbBlogCategory) {
        return blogCategoryService.update(tbBlogCategory);
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public ResponseResult deleteIds(Long[] ids) {
        return blogCategoryService.deleteIds(ids);
    }
}
