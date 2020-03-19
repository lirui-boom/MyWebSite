package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbBlogCategory;

public interface BlogCategoryService {

    /**
     * 查询所有分类
     * @return
     */
    public abstract MyWebSiteResult findAll();

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    public abstract TbBlogCategory findOneById(Long id);

    /**
     * 根据分类名称查询
     * @param name
     * @return
     */
    public abstract TbBlogCategory findOneByName(String name);

    /**
     * 添加分类
     * @param blogCategory
     * @return
     */
    public abstract MyWebSiteResult add(TbBlogCategory blogCategory);

    /**
     * 更新分类
     * @param blogCategory
     * @return
     */
    public abstract MyWebSiteResult update(TbBlogCategory blogCategory);

    /**
     * 删除分类
     * @param ids
     * @return
     */
    public abstract MyWebSiteResult deleteIds(Long[] ids);
}
