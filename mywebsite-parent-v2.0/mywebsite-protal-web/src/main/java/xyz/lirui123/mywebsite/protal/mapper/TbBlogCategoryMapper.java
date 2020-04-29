package xyz.lirui123.mywebsite.protal.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbBlogCategory;
import xyz.lirui123.mywebsite.pojo.TbBlogCategoryExample;

import java.util.List;

@Component
public interface TbBlogCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int countByExample(TbBlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int deleteByExample(TbBlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int insert(TbBlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int insertSelective(TbBlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    List<TbBlogCategory> selectByExample(TbBlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    TbBlogCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TbBlogCategory record, @Param("example") TbBlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TbBlogCategory record, @Param("example") TbBlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbBlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_blog_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbBlogCategory record);
}