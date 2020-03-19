package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbUser;

import java.util.List;

public interface UserService {

    /**
     * 查询所有
     * @return
     */
    public abstract List<TbUser> findAll();
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public abstract TbUser findOneById(Long id);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    public abstract TbUser findOneByEmail(String email);

    /**
     * 条件分页搜索
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract MyWebSiteResult search(TbUser user, int pageNum, int pageSize);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public abstract MyWebSiteResult add(TbUser user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public abstract MyWebSiteResult update(TbUser user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    public abstract MyWebSiteResult deleteIds(Long[] ids);
}
