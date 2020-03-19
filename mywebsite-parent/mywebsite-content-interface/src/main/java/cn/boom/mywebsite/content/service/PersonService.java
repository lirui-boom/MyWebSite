package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbPersonal;

public interface PersonService {

    /**
     * 查询个人信息
     * @param id
     * @return
     */
    public abstract TbPersonal findById(Long id);
    /**
     * 查询个人信息
     * @param name
     * @return
     */
    public abstract TbPersonal findByName(String name);

    /**
     * 修改个人信息
     * @param tbPersonal
     * @return
     */
    public abstract MyWebSiteResult update(TbPersonal tbPersonal);
}
