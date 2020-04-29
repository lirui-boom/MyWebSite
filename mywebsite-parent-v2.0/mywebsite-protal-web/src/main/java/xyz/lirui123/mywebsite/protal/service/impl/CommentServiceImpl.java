package xyz.lirui123.mywebsite.protal.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.pojo.TbBlog;
import xyz.lirui123.mywebsite.pojo.TbComment;
import xyz.lirui123.mywebsite.pojo.TbCommentExample;
import xyz.lirui123.mywebsite.pojo.TbTrends;
import xyz.lirui123.mywebsite.protal.mapper.TbBlogMapper;
import xyz.lirui123.mywebsite.protal.mapper.TbCommentMapper;
import xyz.lirui123.mywebsite.protal.mapper.TbTrendsMapper;
import xyz.lirui123.mywebsite.protal.service.CommentService;
import xyz.lirui123.mywebsite.response.PageResult;
import xyz.lirui123.mywebsite.response.ResponseResult;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TbCommentMapper tbCommentMapper;

    @Autowired
    private TbBlogMapper tbBlogMapper;

    @Autowired
    private TbTrendsMapper tbTrendsMapper;

    @Override
    public TbComment findOneById(Long id) {
        return tbCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseResult search(TbComment tbComment, int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        TbCommentExample example = new TbCommentExample();
        TbCommentExample.Criteria criteria = example.createCriteria();

        if (tbComment == null) {
            return ResponseResult.ok(tbCommentMapper.selectByExample(null));
        }

        if (tbComment.getId() != null) {
            criteria.andIdEqualTo(tbComment.getId());
        }

        if (tbComment.getType() != null && !"".equals(tbComment.getType())) {
            criteria.andTypeEqualTo(tbComment.getType());
        }

        if (tbComment.getStatus() != null && !"".equals(tbComment.getStatus())) {
            criteria.andStatusEqualTo(tbComment.getStatus());
        }

        if (tbComment.getUid() != null) {
            criteria.andUidEqualTo(tbComment.getUid());
        }

        Page page = (Page) tbCommentMapper.selectByExample(example);
        PageResult result = new PageResult(page.getTotal(), page.getResult());
        return ResponseResult.ok(result);
    }

    @Override
    public ResponseResult add(TbComment tbComment) {

        if (tbComment == null || tbComment.getUid() == null || tbComment.getContent() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        if (tbComment.getBlogId() == null && tbComment.getTrendsId() == null || tbComment.getBlogId() != null && tbComment.getTrendsId() != null) {
            return ResponseResult.build(400, "参数不合法");
        }

        if (tbComment.getBlogId() != null) {
            tbComment.setType("0");
        }

        if (tbComment.getTrendsId() != null) {
            tbComment.setType("1");
        }

               tbComment.setStatus("0");
        tbComment.setCreated(new Date());
        tbComment.setPraseCount(0L);
        tbComment.setReplyCount(0L);
        tbCommentMapper.insert(tbComment);

        //维护父评论的评论数
        while (tbComment.getToCid() != null) {
            TbComment parentComment = tbCommentMapper.selectByPrimaryKey(tbComment.getToCid());
            parentComment.setReplyCount(parentComment.getReplyCount() + 1);
            tbCommentMapper.updateByPrimaryKey(parentComment);
            tbComment = parentComment;
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult updateStatusIds(Long[] ids, String status) {

        if (ids == null || ids.length == 0 ||
                status == null || !"0".equals(status) && !"1".equals(status)&& !"2".equals(status)) {
            return ResponseResult.build(400, "参数不合法");
        }

        //判断是否为父评论
        for (Long id : ids) {

            TbComment comment;
            if ((comment = findOneById(id)) != null) {

                TbCommentExample example = new TbCommentExample();
                TbCommentExample.Criteria criteria = example.createCriteria();
                criteria.andToCidEqualTo(comment.getId());
                List<TbComment> comments = tbCommentMapper.selectByExample(example);

                if (comments != null && comments.size() > 0) {
                    return ResponseResult.build(400, "无法审核！此评论包含子评论！");
                }
            }
        }


        for (Long id : ids) {

            TbComment comment;
            if ((comment = findOneById(id)) != null) {
                comment.setStatus(status);
                tbCommentMapper.updateByPrimaryKey(comment);
            }
        }

        //维护评论数
        for (Long id : ids) {

            TbComment comment;
            if ((comment = findOneById(id)) != null) {

                if (comment.getType().equals("0")) {//博客

                    TbCommentExample example = new TbCommentExample();
                    TbCommentExample.Criteria criteria = example.createCriteria();
                    criteria.andBlogIdEqualTo(comment.getBlogId());
                    criteria.andStatusEqualTo("1");
                    List<TbComment> comments = tbCommentMapper.selectByExample(example);

                    TbBlog blog = tbBlogMapper.selectByPrimaryKey(comment.getBlogId());
                    blog.setCommentCount((long) comments.size());
                    tbBlogMapper.updateByPrimaryKey(blog);

                }else { // 动态

                    TbCommentExample example = new TbCommentExample();
                    TbCommentExample.Criteria criteria = example.createCriteria();
                    criteria.andTrendsIdEqualTo(comment.getTrendsId());
                    criteria.andStatusEqualTo("1");
                    List<TbComment> comments = tbCommentMapper.selectByExample(example);

                    TbTrends tbTrends = tbTrendsMapper.selectByPrimaryKey(comment.getTrendsId());
                    tbTrends.setCommentCount((long) comments.size());
                    tbTrendsMapper.updateByPrimaryKey(tbTrends);
                }
            }
        }

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return ResponseResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            TbComment tbComment;

            if ((tbComment = findOneById(id)) != null) {

                tbCommentMapper.deleteByPrimaryKey(id);

                //删除子评论
                TbCommentExample example = new TbCommentExample();
                TbCommentExample.Criteria criteria = example.createCriteria();
                criteria.andToCidEqualTo(id);
                tbCommentMapper.deleteByExample(example);

                //维护评论数

                if (tbComment.getType().equals("0")) {//博客

                    TbCommentExample example1 = new TbCommentExample();
                    TbCommentExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andBlogIdEqualTo(tbComment.getBlogId());
                    criteria1.andStatusEqualTo("1");
                    List<TbComment> comments = tbCommentMapper.selectByExample(example1);

                    TbBlog blog = tbBlogMapper.selectByPrimaryKey(tbComment.getBlogId());
                    blog.setCommentCount((long) comments.size());
                    tbBlogMapper.updateByPrimaryKey(blog);

                }else { // 动态

                    TbCommentExample example1 = new TbCommentExample();
                    TbCommentExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andTrendsIdEqualTo(tbComment.getTrendsId());
                    criteria1.andStatusEqualTo("1");
                    List<TbComment> comments = tbCommentMapper.selectByExample(example1);

                    TbTrends tbTrends = tbTrendsMapper.selectByPrimaryKey(tbComment.getTrendsId());
                    tbTrends.setCommentCount((long) comments.size());
                    tbTrendsMapper.updateByPrimaryKey(tbTrends);
                }
            }
        }

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult update(TbComment tbComment) {

        if (tbComment == null || tbComment.getStatus() == null || tbComment.getUid() == null
                || tbComment.getContent() == null || tbComment.getType() == null
                || tbComment.getId() == null) {
            return ResponseResult.build(400, "参数不合法");
        }
        tbCommentMapper.updateByPrimaryKey(tbComment);
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult getCommentByBlogId(Long blogId) {

        if (blogId == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbCommentExample example = new TbCommentExample();
        TbCommentExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andStatusEqualTo("1");
        //该博客下所有的评论
        List<TbComment> blogCommentsList = tbCommentMapper.selectByExample(example);

        //分离评论和评论回复，转为树状json

        /**
         *
         *  构建格式：   { [ {{},[]} ] }
         *
         */

        //定义格式
        List resultList = new ArrayList(); //[ {{},[]} ]

        List<TbComment> commentsReplyList = new ArrayList<TbComment>();
        List<TbComment> commentsList = new ArrayList<TbComment>();

        for (TbComment tbComment : blogCommentsList) {

            if (tbComment.getToCid() != null) { //评论回复
                commentsReplyList.add(tbComment);
            }else { // 评论
                commentsList.add(tbComment);
            }
        }

        //只展示二级评论 二级及以下为同一级评论

        for (int i = 0; i < commentsList.size(); i++) {

            TbComment tbComment = commentsList.get(i);

            Map one = new HashMap(); //{{},[]}
            one.put("comment",tbComment);

            //二级及以下评论列表
            List<TbComment> childReplyList = new ArrayList<TbComment>();

            for (int j = 0; j < commentsReplyList.size(); j++) {

                TbComment childComment = commentsReplyList.get(j);

                TbComment child = childComment;

                while (child.getToCid() != null) {

                    TbComment parent = tbCommentMapper.selectByPrimaryKey(child.getToCid());

                    if (parent.getId().equals(tbComment.getId())) {
                        childReplyList.add(childComment);
                    }
                    child = parent;
                }
            }

            one.put("childReplyList", childReplyList);
            resultList.add(one);
        }

        return ResponseResult.ok(resultList);
    }

    @Override
    public ResponseResult getCommentByTrendsId(Long trendsId) {
        if (trendsId == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbCommentExample example = new TbCommentExample();
        TbCommentExample.Criteria criteria = example.createCriteria();
        criteria.andTrendsIdEqualTo(trendsId);
        criteria.andStatusEqualTo("1");
        //该动态下所有的评论
        List<TbComment> trendsCommentsList = tbCommentMapper.selectByExample(example);

        //分离评论和评论回复，转为树状json

        /**
         *
         *  构建格式：   { [ {{},[]} ] }
         *
         */

        //定义格式
        List resultList = new ArrayList(); //[ {{},[]} ]

        List<TbComment> commentsReplyList = new ArrayList<TbComment>();
        List<TbComment> commentsList = new ArrayList<TbComment>();

        for (TbComment tbComment : trendsCommentsList) {

            if (tbComment.getToCid() != null) { //评论回复
                commentsReplyList.add(tbComment);
            }else { // 评论
                commentsList.add(tbComment);
            }
        }

        //只展示二级评论 二级及以下为同一级评论

        for (int i = 0; i < commentsList.size(); i++) {

            TbComment tbComment = commentsList.get(i);

            Map one = new HashMap(); //{{},[]}
            one.put("comment",tbComment);

            //二级及以下评论列表
            List<TbComment> childReplyList = new ArrayList<TbComment>();

            for (int j = 0; j < commentsReplyList.size(); j++) {

                TbComment childComment = commentsReplyList.get(j);

                TbComment child = childComment;

                while (child.getToCid() != null) {

                    TbComment parent = tbCommentMapper.selectByPrimaryKey(child.getToCid());

                    if (parent.getId().equals(tbComment.getId())) {
                        childReplyList.add(childComment);
                    }
                    child = parent;
                }
            }

            one.put("childReplyList", childReplyList);
            resultList.add(one);
        }

        return ResponseResult.ok(resultList);
    }

    @Override
    public ResponseResult updateCommentPraseCount(Long id) {

        if (id == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbComment tbComment = tbCommentMapper.selectByPrimaryKey(id);

        if (tbComment == null) {
            return ResponseResult.build(400, "未查询到此评论");
        }

        tbComment.setPraseCount(tbComment.getPraseCount() + 1);
        tbCommentMapper.updateByPrimaryKey(tbComment);
        return ResponseResult.ok();
    }
}
