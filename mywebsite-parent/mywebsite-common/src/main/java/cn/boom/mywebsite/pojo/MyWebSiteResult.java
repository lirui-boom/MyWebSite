package cn.boom.mywebsite.pojo;

import java.io.Serializable;

public class MyWebSiteResult implements Serializable {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    //构建其他状态的PinyougouResult对象
    public static MyWebSiteResult build(Integer status, String msg, Object data) {
        return new MyWebSiteResult(status, msg, data);
    }

    public static MyWebSiteResult ok(Object data) {
        return new MyWebSiteResult(data);
    }

    public static MyWebSiteResult ok() {
        return new MyWebSiteResult(null);
    }

    public MyWebSiteResult() {

    }

    public static MyWebSiteResult build(Integer status, String msg) {
        return new MyWebSiteResult(status, msg, null);
    }

    public MyWebSiteResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public MyWebSiteResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
