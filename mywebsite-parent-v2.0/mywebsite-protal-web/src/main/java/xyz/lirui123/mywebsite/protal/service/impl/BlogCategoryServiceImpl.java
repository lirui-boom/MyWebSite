package xyz.lirui123.mywebsite.protal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.pojo.TbBlogCategory;
import xyz.lirui123.mywebsite.pojo.TbBlogCategoryExample;
import xyz.lirui123.mywebsite.protal.mapper.TbBlogCategoryMapper;
import xyz.lirui123.mywebsite.protal.service.BlogCategoryService;
import xyz.lirui123.mywebsite.response.ResponseResult;

import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private TbBlogCategoryMapper tbBlogCategoryMapper;
    /**
     * 查询所有分类
     * @return
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.ok(tbBlogCategoryMapper.selectByExample(null));
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
    public ResponseResult add(TbBlogCategory blogCategory) {

        if (blogCategory == null || blogCategory.getName() == null || blogCategory.getName().equals("")) {
            return ResponseResult.build(400, "参数不合法");
        }
        tbBlogCategoryMapper.insert(blogCategory);
        return ResponseResult.ok();
    }

    /**
     * 更新分类
     * @param blogCategory
     * @return
     */
    @Override
    public ResponseResult update(TbBlogCategory blogCategory) {

        if (blogCategory == null ||blogCategory.getId() == null
                || blogCategory.getName() == null || blogCategory.getName().equals("")) {
            return ResponseResult.build(400, "参数不合法");
        }

        tbBlogCategoryMapper.updateByPrimaryKey(blogCategory);

        return ResponseResult.ok();
    }
    /**
     * 删除分类
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
                tbBlogCategoryMapper.deleteByPrimaryKey(id);
            }

        }
        return ResponseResult.ok();
    }
}
