package xyz.lirui123.mywebsite.protal.service;

import xyz.lirui123.mywebsite.pojo.TbPersonal;
import xyz.lirui123.mywebsite.response.ResponseResult;

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
    public abstract ResponseResult update(TbPersonal tbPersonal);
}
