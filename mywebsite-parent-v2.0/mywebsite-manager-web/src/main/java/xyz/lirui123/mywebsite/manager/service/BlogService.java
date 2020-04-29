package xyz.lirui123.mywebsite.manager.service;

import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbBlog;
import xyz.lirui123.mywebsite.response.ResponseResult;

public interface BlogService {

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    public abstract TbBlog findOne(Long id);

    /**
     * 有条件的分页查询功能
     * @param tbBlog
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract ResponseResult search(TbBlog tbBlog, int pageNum, int pageSize);

    /**
     * 添加博客
     * @param tbBlog
     * @return
     */
    public abstract ResponseResult add(TbBlog tbBlog);

    /**
     * 更新博客
     * @param tbBlog
     * @return
     */
    public abstract ResponseResult update(TbBlog tbBlog);

    /**
     * 批量审核
     * @param ids
     * @param status
     * @return
     */
    public abstract ResponseResult updateStatusIds(Long[] ids, String status);

    /**
     * 删除博客
     * @param ids
     * @return
     */
    public abstract ResponseResult deleteIds(Long[] ids);
}
