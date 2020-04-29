package xyz.lirui123.mywebsite.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbComment;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {

        TbComment comment = commentService.findOneById(id);
        if (comment == null) {
            return ResponseResult.build(400, "未查询到此评论");
        }
        return ResponseResult.ok(comment);
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody TbComment tbComment, int page, int rows) {
        return commentService.search(tbComment,page,rows);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestBody TbComment tbComment) {
        return commentService.add(tbComment);
    }


    @RequestMapping("/updateStatusIds")
    @ResponseBody
    public ResponseResult updateStatusIds(Long[] ids, String status) {
        return commentService.updateStatusIds(ids, status);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public ResponseResult deleteIds(Long[] ids) {
        return commentService.deleteIds(ids);
    }


}
