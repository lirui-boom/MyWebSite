package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbTrends;

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
    public abstract MyWebSiteResult search(TbTrends tbTrends, int pageNum, int pageSize);

    /**
     * 添加动态
     * @param tbTrends
     * @return
     */
    public abstract MyWebSiteResult add(TbTrends tbTrends);

    /**
     * 修改动态
     * @param tbTrends
     * @return
     */
    public abstract MyWebSiteResult update(TbTrends tbTrends);

    /**
     * 删除动态
     * @param ids
     * @return
     */
    public abstract MyWebSiteResult deleteIds(Long[] ids);

    /**
     * 批量修改动态状态
     * @param ids
     * @param status
     * @return
     */
    public abstract MyWebSiteResult updateStatusIds(Long[] ids, String status);
}
