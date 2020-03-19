package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbComment;

public interface CommentService {

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public abstract TbComment findOneById(Long id);

    /**
     * 条件分页搜索
     * @param tbComment
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract MyWebSiteResult search(TbComment tbComment, int pageNum, int pageSize);


    /**
     * 添加评论
     * @param tbComment
     * @return
     */
    public abstract MyWebSiteResult add(TbComment tbComment);

    /**
     * 批量审核
     * @param ids
     * @param status
     * @return
     */
    public abstract MyWebSiteResult updateStatusIds(Long[] ids, String status);

    /**
     * 批量删除（关联删除）
     * @param ids
     * @return
     */
    public abstract MyWebSiteResult deleteIds(Long[] ids);

    /**
     * 修改评论信息
     * @param tbComment
     * @return
     */
    public abstract MyWebSiteResult update(TbComment tbComment);

    /**
     * 通过博客id查询所有评论
     * @param blogId
     * @return
     */
    public abstract MyWebSiteResult getCommentByBlogId(Long blogId);

    /**
     * 通过动态id查询所有评论
     * @param trendsId
     * @return
     */
    public abstract MyWebSiteResult getCommentByTrendsId(Long trendsId);

    /**
     * 点赞
     * @param id
     * @return
     */
    public abstract MyWebSiteResult updateCommentPraseCount(Long id);
}
