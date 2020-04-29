package xyz.lirui123.mywebsite.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbContact implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.uid
     *
     * @mbggenerated
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.message
     *
     * @mbggenerated
     */
    private String message;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.created
     *
     * @mbggenerated
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.reply_msg
     *
     * @mbggenerated
     */
    private String replyMsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.replied
     *
     * @mbggenerated
     */
    private Date replied;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contact.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.id
     *
     * @return the value of tb_contact.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.id
     *
     * @param id the value for tb_contact.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.uid
     *
     * @return the value of tb_contact.uid
     *
     * @mbggenerated
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.uid
     *
     * @param uid the value for tb_contact.uid
     *
     * @mbggenerated
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.message
     *
     * @return the value of tb_contact.message
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.message
     *
     * @param message the value for tb_contact.message
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.created
     *
     * @return the value of tb_contact.created
     *
     * @mbggenerated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.created
     *
     * @param created the value for tb_contact.created
     *
     * @mbggenerated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.reply_msg
     *
     * @return the value of tb_contact.reply_msg
     *
     * @mbggenerated
     */
    public String getReplyMsg() {
        return replyMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.reply_msg
     *
     * @param replyMsg the value for tb_contact.reply_msg
     *
     * @mbggenerated
     */
    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg == null ? null : replyMsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.replied
     *
     * @return the value of tb_contact.replied
     *
     * @mbggenerated
     */
    public Date getReplied() {
        return replied;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.replied
     *
     * @param replied the value for tb_contact.replied
     *
     * @mbggenerated
     */
    public void setReplied(Date replied) {
        this.replied = replied;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_contact.status
     *
     * @return the value of tb_contact.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_contact.status
     *
     * @param status the value for tb_contact.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}