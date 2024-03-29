package cn.boom.mywebsite.mapper;

import cn.boom.mywebsite.pojo.TbContact;
import cn.boom.mywebsite.pojo.TbContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContactMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int countByExample(TbContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int deleteByExample(TbContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int insert(TbContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int insertSelective(TbContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    List<TbContact> selectByExample(TbContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    TbContact selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TbContact record, @Param("example") TbContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TbContact record, @Param("example") TbContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_contact
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbContact record);
}