package xyz.lirui123.mywebsite.manager.service;


import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbBlogCategory;
import xyz.lirui123.mywebsite.response.ResponseResult;

public interface BlogCategoryService {

    /**
     * 查询所有分类
     * @return
     */
    public abstract ResponseResult findAll();

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
    public abstract ResponseResult add(TbBlogCategory blogCategory);

    /**
     * 更新分类
     * @param blogCategory
     * @return
     */
    public abstract ResponseResult update(TbBlogCategory blogCategory);

    /**
     * 删除分类
     * @param ids
     * @return
     */
    public abstract ResponseResult deleteIds(Long[] ids);
}
