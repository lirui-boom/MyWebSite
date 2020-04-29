package xyz.lirui123.mywebsite.manager.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import xyz.lirui123.mywebsite.pojo.TbAdmin;
import xyz.lirui123.mywebsite.response.ResponseResult;

public interface AdminService extends UserDetailsService {

    /**
     * 根据用户名查询用户
     *
     * @param admin
     * @return
     */
    public abstract TbAdmin findOneByAdmin(String admin);

    /**
     * 根据Id查询用户
     *
     * @param id
     * @return
     */
    public abstract TbAdmin findOneById(Long id);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    public abstract TbAdmin findOneByEmail(String email);


    /**
     * 添加用户
     *
     * @param admin
     */
    public abstract ResponseResult add(TbAdmin admin);

    /**
     * 账号激活
     *
     * @param id
     * @param token
     * @return
     */
    public abstract ResponseResult active(Long id, String token);

    /**
     * 条件分页查询
     *
     * @param admin
     * @param pageSize
     * @param pageNum
     * @return
     */
    public abstract ResponseResult search(TbAdmin admin, int pageSize, int pageNum);

    /**
     * 修改用户信息
     *
     * @param admin
     * @return
     */
    public abstract ResponseResult update(TbAdmin admin);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    public abstract ResponseResult delete(Long[] ids);

    /**
     * 审核用户
     * @param ids
     * @param status
     * @return
     */
    public abstract ResponseResult updateStatusIds(Long[] ids, String status);

    /**
     * 修改密码
     * @param admin
     * @return
     */
    public abstract ResponseResult updatePassword(TbAdmin admin);

    /**
     * 发送密码验证邮箱
     * @param id
     * @return
     */
    public abstract ResponseResult sendPasswordEmail(Long id);

    /**
     * 修改用户邮箱
     * @param admin
     * @return
     */
    public abstract ResponseResult updateEmail(TbAdmin admin);

    /**
     * 发送邮箱绑定验证邮箱
     * @param admin
     * @return
     */
    public abstract ResponseResult sendReEmail(TbAdmin admin);
}

