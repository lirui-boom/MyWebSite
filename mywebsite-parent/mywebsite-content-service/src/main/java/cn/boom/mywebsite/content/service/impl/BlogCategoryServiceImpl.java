package cn.boom.mywebsite.content.service.impl;

import cn.boom.mywebsite.content.service.BlogCategoryService;
import cn.boom.mywebsite.mapper.TbBlogCategoryMapper;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbBlogCategory;
import cn.boom.mywebsite.pojo.TbBlogCategoryExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000)
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private TbBlogCategoryMapper tbBlogCategoryMapper;
    /**
     * 查询所有分类
     * @return
     */
    @Override
    public MyWebSiteResult findAll() {
        return MyWebSiteResult.ok(tbBlogCategoryMapper.selectByExample(null));
    }
    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public TbBlogCategory findOneById(Long id) {
        return tbBlogCategoryMapper.selectByPrimaryKey(id);
    }
    /**
     * 根据分类名称查询
     * @param name
     * @return
     */
    @Override
    public TbBlogCategory findOneByName(String name) {

        TbBlogCategoryExample example = new TbBlogCategoryExample();
        TbBlogCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);

        List<TbBlogCategory> blogCategoryList = tbBlogCategoryMapper.selectByExample(example);

        if (blogCategoryList != null && blogCategoryList.size() > 0) {
            return blogCategoryList.get(0);
        }
        return null;
    }
    /**
     * 添加分类
     * @param blogCategory
     * @return
     */
    @Override
    public MyWebSiteResult add(TbBlogCategory blogCategory) {

        if (blogCategory == null || blogCategory.getName() == null || blogCategory.getName().equals("")) {
            return MyWebSiteResult.build(400, "参数不合法");
        }
        tbBlogCategoryMapper.insert(blogCategory);
        return MyWebSiteResult.ok();
    }

    /**
     * 更新分类
     * @param blogCategory
     * @return
     */
    @Override
    public MyWebSiteResult update(TbBlogCategory blogCategory) {

        if (blogCategory == null ||blogCategory.getId() == null
                || blogCategory.getName() == null || blogCategory.getName().equals("")) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        tbBlogCategoryMapper.updateByPrimaryKey(blogCategory);

        return MyWebSiteResult.ok();
    }
    /**
     * 删除分类
     * @param ids
     * @return
     */
    @Override
    public MyWebSiteResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbBlogCategoryMapper.deleteByPrimaryKey(id);
            }

        }
        return MyWebSiteResult.ok();
    }
}
