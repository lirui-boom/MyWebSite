package cn.boom.mywebsite.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbTrends implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.tremds_desc
     *
     * @mbggenerated
     */
    private String tremdsDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.pic_url
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.updated
     *
     * @mbggenerated
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.prase_count
     *
     * @mbggenerated
     */
    private Long praseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.comment_count
     *
     * @mbggenerated
     */
    private Long commentCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_trends.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.id
     *
     * @return the value of tb_trends.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.id
     *
     * @param id the value for tb_trends.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.title
     *
     * @return the value of tb_trends.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.title
     *
     * @param title the value for tb_trends.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.content
     *
     * @return the value of tb_trends.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.content
     *
     * @param content the value for tb_trends.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.tremds_desc
     *
     * @return the value of tb_trends.tremds_desc
     *
     * @mbggenerated
     */
    public String getTremdsDesc() {
        return tremdsDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.tremds_desc
     *
     * @param tremdsDesc the value for tb_trends.tremds_desc
     *
     * @mbggenerated
     */
    public void setTremdsDesc(String tremdsDesc) {
        this.tremdsDesc = tremdsDesc == null ? null : tremdsDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.pic_url
     *
     * @return the value of tb_trends.pic_url
     *
     * @mbggenerated
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.pic_url
     *
     * @param picUrl the value for tb_trends.pic_url
     *
     * @mbggenerated
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.updated
     *
     * @return the value of tb_trends.updated
     *
     * @mbggenerated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.updated
     *
     * @param updated the value for tb_trends.updated
     *
     * @mbggenerated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.prase_count
     *
     * @return the value of tb_trends.prase_count
     *
     * @mbggenerated
     */
    public Long getPraseCount() {
        return praseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.prase_count
     *
     * @param praseCount the value for tb_trends.prase_count
     *
     * @mbggenerated
     */
    public void setPraseCount(Long praseCount) {
        this.praseCount = praseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.comment_count
     *
     * @return the value of tb_trends.comment_count
     *
     * @mbggenerated
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.comment_count
     *
     * @param commentCount the value for tb_trends.comment_count
     *
     * @mbggenerated
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_trends.status
     *
     * @return the value of tb_trends.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_trends.status
     *
     * @param status the value for tb_trends.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}