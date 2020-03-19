package cn.boom.mywebsite.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbBlog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.blog_desc
     *
     * @mbggenerated
     */
    private String blogDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.pic_url
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.updated
     *
     * @mbggenerated
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.cate_id
     *
     * @mbggenerated
     */
    private Long cateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.prase_count
     *
     * @mbggenerated
     */
    private Long praseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_blog.comment_count
     *
     * @mbggenerated
     */
    private Long commentCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.id
     *
     * @return the value of tb_blog.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.id
     *
     * @param id the value for tb_blog.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.title
     *
     * @return the value of tb_blog.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.title
     *
     * @param title the value for tb_blog.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.content
     *
     * @return the value of tb_blog.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.content
     *
     * @param content the value for tb_blog.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.blog_desc
     *
     * @return the value of tb_blog.blog_desc
     *
     * @mbggenerated
     */
    public String getBlogDesc() {
        return blogDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.blog_desc
     *
     * @param blogDesc the value for tb_blog.blog_desc
     *
     * @mbggenerated
     */
    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc == null ? null : blogDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.pic_url
     *
     * @return the value of tb_blog.pic_url
     *
     * @mbggenerated
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.pic_url
     *
     * @param picUrl the value for tb_blog.pic_url
     *
     * @mbggenerated
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.updated
     *
     * @return the value of tb_blog.updated
     *
     * @mbggenerated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.updated
     *
     * @param updated the value for tb_blog.updated
     *
     * @mbggenerated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.status
     *
     * @return the value of tb_blog.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.status
     *
     * @param status the value for tb_blog.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.cate_id
     *
     * @return the value of tb_blog.cate_id
     *
     * @mbggenerated
     */
    public Long getCateId() {
        return cateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.cate_id
     *
     * @param cateId the value for tb_blog.cate_id
     *
     * @mbggenerated
     */
    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.prase_count
     *
     * @return the value of tb_blog.prase_count
     *
     * @mbggenerated
     */
    public Long getPraseCount() {
        return praseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.prase_count
     *
     * @param praseCount the value for tb_blog.prase_count
     *
     * @mbggenerated
     */
    public void setPraseCount(Long praseCount) {
        this.praseCount = praseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_blog.comment_count
     *
     * @return the value of tb_blog.comment_count
     *
     * @mbggenerated
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_blog.comment_count
     *
     * @param commentCount the value for tb_blog.comment_count
     *
     * @mbggenerated
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}