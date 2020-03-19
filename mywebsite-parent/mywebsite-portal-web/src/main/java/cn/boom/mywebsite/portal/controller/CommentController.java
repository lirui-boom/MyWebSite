package cn.boom.mywebsite.portal.controller;

import cn.boom.mywebsite.content.service.CommentService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbComment;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Reference
    private CommentService commentService;

    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {

        TbComment comment = commentService.findOneById(id);
        if (comment == null) {
            return MyWebSiteResult.build(400, "未查询到此评论");
        }
        return MyWebSiteResult.ok(comment);
    }

    @RequestMapping("/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbComment tbComment, int page, int rows) {
        return commentService.search(tbComment,page,rows);
    }

    @RequestMapping("/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbComment tbComment) {
        return commentService.add(tbComment);
    }

    @RequestMapping("/getCommentByBlogId")
    @ResponseBody
    public MyWebSiteResult getCommentByBlogId(Long blogId) {
        return commentService.getCommentByBlogId(blogId);
    }

    @RequestMapping("/getCommentByTrendsId")
    @ResponseBody
    public MyWebSiteResult getCommentByTrendsId(Long trendsId) {
        return commentService.getCommentByTrendsId(trendsId);
    }

    @RequestMapping("/updateCommentPraseCount")
    @ResponseBody
    public MyWebSiteResult updateCommentPraseCount(Long id) {
        return commentService.updateCommentPraseCount(id);
    }

}
