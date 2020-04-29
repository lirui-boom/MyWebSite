package xyz.lirui123.mywebsite.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbComment;
import xyz.lirui123.mywebsite.pojo.TbCommentExample;

import java.util.List;

@Component
public interface TbCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int countByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int deleteByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int insert(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int insertSelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    List<TbComment> selectByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    TbComment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TbComment record, @Param("example") TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TbComment record, @Param("example") TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbComment record);
}