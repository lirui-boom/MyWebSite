package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbBlog;

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
    public abstract MyWebSiteResult search(TbBlog tbBlog, int pageNum, int pageSize);

    /**
     * 添加博客
     * @param tbBlog
     * @return
     */
    public abstract MyWebSiteResult add(TbBlog tbBlog);

    /**
     * 更新博客
     * @param tbBlog
     * @return
     */
    public abstract MyWebSiteResult update(TbBlog tbBlog);

    /**
     * 批量审核
     * @param ids
     * @param status
     * @return
     */
    public abstract MyWebSiteResult updateStatusIds(Long[] ids, String status);

    /**
     * 删除博客
     * @param ids
     * @return
     */
    public abstract MyWebSiteResult deleteIds(Long[] ids);
}
