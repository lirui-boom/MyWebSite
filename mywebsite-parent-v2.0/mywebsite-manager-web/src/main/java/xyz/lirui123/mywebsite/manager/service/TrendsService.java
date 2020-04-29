package xyz.lirui123.mywebsite.manager.service;


import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbTrends;
import xyz.lirui123.mywebsite.response.ResponseResult;

public interface TrendsService {

    /**
     * 根据id查询动态记录
     * @param id
     * @return
     */
    public abstract TbTrends findOneById(Long id);

    /**
     * 条件分页搜索
     * @param tbTrends
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract ResponseResult search(TbTrends tbTrends, int pageNum, int pageSize);

    /**
     * 添加动态
     * @param tbTrends
     * @return
     */
    public abstract ResponseResult add(TbTrends tbTrends);

    /**
     * 修改动态
     * @param tbTrends
     * @return
     */
    public abstract ResponseResult update(TbTrends tbTrends);

    /**
     * 删除动态
     * @param ids
     * @return
     */
    public abstract ResponseResult deleteIds(Long[] ids);

    /**
     * 批量修改动态状态
     * @param ids
     * @param status
     * @return
     */
    public abstract ResponseResult updateStatusIds(Long[] ids, String status);
}
