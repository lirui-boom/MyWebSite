package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.content.service.BlogCategoryService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbBlogCategory;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/blogCategory")
public class BlogCategoryController {

    @Reference
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/findAll")
    @ResponseBody
    public MyWebSiteResult findAll() {
        return blogCategoryService.findAll();
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {

        if (id == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        TbBlogCategory blogCategory = blogCategoryService.findOneById(id);

        if (blogCategory == null) {
            return MyWebSiteResult.build(400, "未查询到此分类");
        }

        return MyWebSiteResult.ok(blogCategory);
    }

    @RequestMapping("/findOneByName")
    @ResponseBody
    public MyWebSiteResult findOneByName(String name) {

        if (name == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        TbBlogCategory blogCategory = blogCategoryService.findOneByName(name);

        if (blogCategory == null) {
            return MyWebSiteResult.build(400, "未查询到此分类");
        }

        return MyWebSiteResult.ok(blogCategory);
    }

    @RequestMapping("add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbBlogCategory tbBlogCategory) {
        return blogCategoryService.add(tbBlogCategory);
    }

    @RequestMapping("update")
    @ResponseBody
    public MyWebSiteResult update(@RequestBody TbBlogCategory tbBlogCategory) {
        return blogCategoryService.update(tbBlogCategory);
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public MyWebSiteResult deleteIds(Long[] ids) {
        return blogCategoryService.deleteIds(ids);
    }
}
