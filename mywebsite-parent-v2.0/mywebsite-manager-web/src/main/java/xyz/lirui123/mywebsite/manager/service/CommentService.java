package xyz.lirui123.mywebsite.manager.service;


import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbComment;
import xyz.lirui123.mywebsite.response.ResponseResult;

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
    public abstract ResponseResult search(TbComment tbComment, int pageNum, int pageSize);


    /**
     * 添加评论
     * @param tbComment
     * @return
     */
    public abstract ResponseResult add(TbComment tbComment);

    /**
     * 批量审核
     * @param ids
     * @param status
     * @return
     */
    public abstract ResponseResult updateStatusIds(Long[] ids, String status);

    /**
     * 批量删除（关联删除）
     * @param ids
     * @return
     */
    public abstract ResponseResult deleteIds(Long[] ids);

    /**
     * 修改评论信息
     * @param tbComment
     * @return
     */
    public abstract ResponseResult update(TbComment tbComment);

    /**
     * 通过博客id查询所有评论
     * @param blogId
     * @return
     */
    public abstract ResponseResult getCommentByBlogId(Long blogId);

    /**
     * 通过动态id查询所有评论
     * @param trendsId
     * @return
     */
    public abstract ResponseResult getCommentByTrendsId(Long trendsId);

    /**
     * 点赞
     * @param id
     * @return
     */
    public abstract ResponseResult updateCommentPraseCount(Long id);
}
