package xyz.lirui123.mywebsite.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.manager.mapper.TbTrendsMapper;
import xyz.lirui123.mywebsite.pojo.TbTrends;
import xyz.lirui123.mywebsite.pojo.TbTrendsExample;
import xyz.lirui123.mywebsite.response.PageResult;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.TrendsService;

import java.util.Date;

@PropertySource(value = {"classpath:properties/DefaultPicPath.properties"}, encoding = "utf-8")
@Service
public class TrendsServiceImpl implements TrendsService {

    @Autowired
    private TbTrendsMapper tbTrendsMapper;

    @Value("${DEFAULT_TRENDS_IMAGE}")
    private String DEFAULT_TRENDS_IMAGE;
    /**
     * 根据id查询动态记录
     * @param id
     * @return
     */
    @Override
    public TbTrends findOneById(Long id) {
        return tbTrendsMapper.selectByPrimaryKey(id);
    }

    /**
     * 条件分页搜索
     * @param tbTrends
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult search(TbTrends tbTrends, int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        TbTrendsExample example = new TbTrendsExample();
        TbTrendsExample.Criteria criteria = example.createCriteria();

        if (tbTrends.getId() != null) {
            criteria.andIdEqualTo(tbTrends.getId());
        }

        if (tbTrends.getTitle() != null && !"".equals(tbTrends.getTitle())) {
            criteria.andTitleLike("%" + tbTrends.getTitle() + "%");
        }

        if (tbTrends.getStatus() != null && !"".equals(tbTrends.getStatus())) {
            criteria.andStatusEqualTo(tbTrends.getStatus());
        }


        Page page = (Page) tbTrendsMapper.selectByExample(example);
        PageResult result = new PageResult(page.getTotal(), page.getResult());
        return ResponseResult.ok(result);
    }

    /**
     * 添加动态
     * @param tbTrends
     * @return
     */
    @Override
    public ResponseResult add(TbTrends tbTrends) {

        if (tbTrends.getTitle() == null || tbTrends.getContent() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        tbTrends.setStatus("0");
        tbTrends.setUpdated(new Date());
        tbTrends.setCommentCount(0L);
        tbTrends.setPraseCount(0L);

        if (tbTrends.getPicUrl() == null) {
            tbTrends.setPicUrl(DEFAULT_TRENDS_IMAGE);
        }

        tbTrendsMapper.insert(tbTrends);

        return ResponseResult.ok();
    }

    /**
     * 修改动态
     * @param tbTrends
     * @return
     */
    @Override
    public ResponseResult update(TbTrends tbTrends) {

        if (tbTrends.getId() == null || tbTrends.getStatus() == null || tbTrends.getTitle() == null || tbTrends.getContent() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        tbTrendsMapper.updateByPrimaryKey(tbTrends);
        return ResponseResult.ok();
    }

    /**
     * 删除动态
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return ResponseResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbTrendsMapper.deleteByPrimaryKey(id);
            }
        }
        return ResponseResult.ok();
    }

    /**
     * 批量修改动态状态
     * @param ids
     * @param status
     * @return
     */
    @Override
    public ResponseResult updateStatusIds(Long[] ids, String status) {

        if (ids == null || ids.length == 0 || !"0".equals(status) && !"1".equals(status)) {
            return ResponseResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            TbTrends tbTrends;

            if ((tbTrends = findOneById(id)) != null) {
                tbTrends.setStatus(status);
                tbTrendsMapper.updateByPrimaryKey(tbTrends);
            }
        }
        return ResponseResult.ok();
    }
}
