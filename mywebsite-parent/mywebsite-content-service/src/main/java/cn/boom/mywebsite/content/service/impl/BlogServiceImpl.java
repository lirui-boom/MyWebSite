package cn.boom.mywebsite.content.service.impl;

import cn.boom.mywebsite.content.service.BlogService;
import cn.boom.mywebsite.mapper.TbBlogMapper;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.PageResult;
import cn.boom.mywebsite.pojo.TbBlog;
import cn.boom.mywebsite.pojo.TbBlogExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service(timeout = 3000)
public class BlogServiceImpl implements BlogService {

    @Autowired
    private TbBlogMapper tbBlogMapper;

    @Value("${DEFAULT_BLOG_IMAGE}")
    private String DEFAULT_BLOG_IMAGE;

    @Override
    public TbBlog findOne(Long id) {
        return tbBlogMapper.selectByPrimaryKey(id);
    }

    @Override
    public MyWebSiteResult search(TbBlog tbBlog, int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbBlogExample example = new TbBlogExample();

        TbBlogExample.Criteria criteria = example.createCriteria();

        if (tbBlog == null) {
            tbBlog = new TbBlog();
        }

        if (tbBlog.getId() != null) {
            criteria.andIdEqualTo(tbBlog.getId());
        }

        if (tbBlog.getTitle() != null && !"".equals(tbBlog.getTitle())) {
            criteria.andTitleLike("%" + tbBlog.getTitle() + "%");
        }

        if (tbBlog.getStatus() != null && !"".equals(tbBlog.getStatus())) {
            criteria.andStatusEqualTo(tbBlog.getStatus());
        }

        if (tbBlog.getCateId() != null) {
            criteria.andCateIdEqualTo(tbBlog.getCateId());
        }

        Page page = (Page) tbBlogMapper.selectByExample(example);
        PageResult result = new PageResult(page.getTotal(), page.getResult());

        return MyWebSiteResult.ok(result);
    }

    @Override
    public MyWebSiteResult add(TbBlog tbBlog) {

        if (tbBlog == null) {
            return MyWebSiteResult.build(400, "从参数不合法");
        }

        tbBlog.setStatus("0");
        tbBlog.setUpdated(new Date());
        tbBlog.setPraseCount(0L);
        tbBlog.setCommentCount(0L);

        if (tbBlog.getPicUrl() == null) {
            tbBlog.setPicUrl(DEFAULT_BLOG_IMAGE);
        }

        tbBlogMapper.insert(tbBlog);
        return MyWebSiteResult.ok();
}

    @Override
    public MyWebSiteResult update(TbBlog tbBlog) {

        if (tbBlog == null || tbBlog.getId() == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        tbBlog.setUpdated(new Date());

        tbBlogMapper.updateByPrimaryKey(tbBlog);

        return MyWebSiteResult.ok();
    }

    @Override
    public MyWebSiteResult updateStatusIds(Long[] ids, String status) {

        if (ids == null || !"1".equals(status) && !"0".equals(status)) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            TbBlog blog;

            if ((blog = findOne(id)) != null) {
                blog.setUpdated(new Date());
                blog.setStatus(status);
                tbBlogMapper.updateByPrimaryKey(blog);
            }
        }

        return MyWebSiteResult.ok();
    }

    @Override
    public MyWebSiteResult deleteIds(Long[] ids) {

        if (ids == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }
        for (Long id : ids) {

            if (findOne(id) != null) {

                tbBlogMapper.deleteByPrimaryKey(id);
            }
        }
        return MyWebSiteResult.ok();
    }
}
