package xyz.lirui123.mywebsite.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.manager.mapper.TbAdminMapper;
import xyz.lirui123.mywebsite.pojo.AdminRole;
import xyz.lirui123.mywebsite.pojo.TbAdmin;
import xyz.lirui123.mywebsite.pojo.TbAdminExample;
import xyz.lirui123.mywebsite.response.PageResult;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.AdminService;
import xyz.lirui123.mywebsite.utils.BCryptUtil;
import xyz.lirui123.mywebsite.utils.DateUtil;
import xyz.lirui123.mywebsite.utils.MailUtils;
import xyz.lirui123.mywebsite.utils.TokenUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@PropertySource(value = {"classpath:properties/DefaultPicPath.properties","classpath:properties/TokenMail.properties"}, encoding = "utf-8")
@Service
public class AdminServiceImpl implements AdminService {

    @Value("${TOKEN_REGISTER_URL}")
    private String TOKEN_REGISTER_URL;
    @Value("${TOKEN_REGISTER_TITLE}")
    private String TOKEN_REGISTER_TITLE;
    @Value("${TOKEN_REGISTER_CONTENT}")
    private String TOKEN_REGISTER_CONTENT;
    @Value("${TOKEN_PASSWORD_TITLE}")
    private String TOKEN_PASSWORD_TITLE;
    @Value("${TOKEN_PASSWORD_CONTENT}")
    private String TOKEN_PASSWORD_CONTENT;
    @Value("${TOKEN_REEMAIL_TITLE}")
    private String TOKEN_REEMAIL_TITLE;
    @Value("${TOKEN_REEMAIL_CONTENT}")
    private String TOKEN_REEMAIL_CONTENT;
    @Value("${DEFAULT_ADMIN_IMAGE}")
    private String DEFAULT_ADMIN_IMAGE;

    @Autowired
    private TbAdminMapper tbAdminMapper;


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据用户名查询用户
     * @param admin
     * @return
     */
    @Override
    public TbAdmin findOneByAdmin(String admin) {

        TbAdminExample example = new TbAdminExample();

        TbAdminExample.Criteria criteria = example.createCriteria();

        criteria.andAdminEqualTo(admin);

        List<TbAdmin> adminList = tbAdminMapper.selectByExample(example);

        if (adminList != null && adminList.size() > 0) {

            return adminList.get(0);
        }
        return null;
    }

    @Override
    public TbAdmin findOneById(Long id) {
        return tbAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public TbAdmin findOneByEmail(String email) {

        TbAdminExample example = new TbAdminExample();

        TbAdminExample.Criteria criteria = example.createCriteria();

        criteria.andEmailEqualTo(email.toLowerCase());

        List<TbAdmin> adminList = tbAdminMapper.selectByExample(example);

        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }

        return null;
    }

    /**
     * 添加用户
     * @param admin
     */
    @Override
    public ResponseResult add(TbAdmin admin) {

        if (admin.getAdmin() == null || admin.getAdmin().length() == 0 || admin.getPassword() == null ||
                admin.getPassword().length() == 0 || admin.getEmail() == null || admin.getEmail().length() == 0) {
            throw new IllegalArgumentException(" Add Error ! This Admin is illegal argument. ");
        }

        if (findOneByAdmin(admin.getAdmin()) != null) {
            return ResponseResult.build(400, "该用户名已存在!");
        }

        if (findOneByEmail(admin.getEmail()) != null) {
            return ResponseResult.build(400, "该邮箱已绑定过账号!");
        }

        admin.setCreated(new Date());
        admin.setPassword(BCryptUtil.encode(admin.getPassword()));//加密
        admin.setToken(UUID.randomUUID().toString().replace("-","").substring(0,15));
        admin.setStatus("-1");
        admin.setEmail(admin.getEmail().toLowerCase());//转小写
        admin.setPicUrl(DEFAULT_ADMIN_IMAGE);

        tbAdminMapper.insert(admin);

        sendTokenMail(admin);

        return ResponseResult.ok();
    }

    /**
     * 激活用户
     * @param id
     * @param token
     * @return
     */
    @Override
    public ResponseResult active(Long id, String token) {

        TbAdmin admin = findOneById(id);

        if (admin == null) {
            return ResponseResult.build(400, "未查询到此用户！");
        }

        if (!admin.getToken().equals(token)) {
            return ResponseResult.build(400, "激活码错误！");
        }else {
            admin.setStatus("0");
            admin.setToken("");
            tbAdminMapper.updateByPrimaryKey(admin);
            return ResponseResult.ok();
        }
    }

    /**
     * 条件分页查询
     * @param admin
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public ResponseResult search(TbAdmin admin, int pageSize, int pageNum) {

        if (pageSize == 0) {
            pageSize = 5;
        }

        if (pageNum == 0) {
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbAdminExample example = new TbAdminExample();

        TbAdminExample.Criteria criteria = example.createCriteria();

        if (admin.getId() != null) {
            criteria.andIdEqualTo(admin.getId());
        }

        if (admin.getAdmin() != null && !"".equals(admin.getAdmin())) {
            criteria.andAdminLike("%" + admin.getAdmin() + "%");
        }

        if (admin.getName() != null && !"".equals(admin.getName())) {
            criteria.andNameLike("%" + admin.getName() + "%");
        }

        if (admin.getStatus() != null && !"".equals(admin.getStatus())) {
            criteria.andStatusEqualTo(admin.getStatus());
        }

        if (admin.getEmail() != null && !"".equals(admin.getEmail())) {
            criteria.andEmailEqualTo(admin.getEmail().toLowerCase());
        }

        if (admin.getPhone() != null && !"".equals(admin.getPhone())) {
            criteria.andPhoneEqualTo(admin.getPhone());
        }

        Page page = (Page) tbAdminMapper.selectByExample(example);

        List<TbAdmin> adminList = page.getResult();

        for (TbAdmin tbAdmin : adminList) {
            tbAdmin.setPassword(null); //隐藏密码
            tbAdmin.setToken(null);
        }

        PageResult result = new PageResult(page.getTotal(), adminList);

        return ResponseResult.ok(result);
    }

    /***
     * 修改用户
     * @param admin
     * @return
     */
    @Override
    public ResponseResult update(TbAdmin admin) {

        if (admin.getId() == null || admin.getId() <= 0) {
            return ResponseResult.build(400, "参数不合法");
        }

        admin.setPassword( tbAdminMapper.selectByPrimaryKey(admin.getId()).getPassword());

        tbAdminMapper.updateByPrimaryKey(admin);
        return ResponseResult.ok();
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @Override
    public ResponseResult delete(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return ResponseResult.build(400, "参数不合法");
        }

        for (int i = 0; i < ids.length; i++) {

            if (findOneById(ids[i]) != null) {
                tbAdminMapper.deleteByPrimaryKey(ids[i]);
            }
        }
        
        return ResponseResult.ok();
    }

    /**
     * 审核用户
     * @param ids
     * @param status
     * @return
     */
    @Override
    public ResponseResult updateStatusIds(Long[] ids, String status) {

        if (status == null || !status.equals("-1") && !status.equals("0") && !status.equals("1") && !status.equals("2")
            || ids == null || ids.length == 0) {
            return ResponseResult.build(400, "参数不合法");
        }

        for (int i = 0; i < ids.length; i++) {

            TbAdmin admin;
            if ((admin = findOneById(ids[i])) != null && !admin.getStatus().equals("-1")) {
                admin.setStatus(status);
                tbAdminMapper.updateByPrimaryKey(admin);
            }
        }

        return ResponseResult.ok();
    }

    /**
     * 修改用户密码
     * @param admin
     * @return
     */
    @Override
    public ResponseResult updatePassword(TbAdmin admin) {

        if (admin == null || admin.getId() == null || admin.getStatus() == null
                || admin.getEmail() == null || admin.getAdmin() == null
                || admin.getPassword() == null || admin.getToken() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbAdmin oldAdmin = tbAdminMapper.selectByPrimaryKey(admin.getId());

        if (oldAdmin == null) {
            return  ResponseResult.build(400, "参数不合法");
        }

        if (!admin.getToken().equals(redisTemplate.boundHashOps("mywebsite-"+oldAdmin.getId()).get("psw-email"))) {
            return ResponseResult.build(400, "验证码有误或已过期！");
        }

        admin.setPassword(BCryptUtil.encode(admin.getPassword()));//加密
        tbAdminMapper.updateByPrimaryKey(admin);
        redisTemplate.boundHashOps("mywebsite-" + oldAdmin.getId()).delete("psw-email");

        return ResponseResult.ok();
    }

    /**
     * 发送密码验证邮箱
     * @param id
     * @return
     */
    @Override
    public ResponseResult sendPasswordEmail(Long id) {

        TbAdmin tbAdmin = tbAdminMapper.selectByPrimaryKey(id);

        if (tbAdmin == null) {
            return ResponseResult.build(400, "验证码有误");
        }

        String token = TokenUtils.getToken();

        redisTemplate.boundHashOps("mywebsite-" + tbAdmin.getId()).put("psw-email", token);
        redisTemplate.expire("mywebsite-" + tbAdmin.getId(), 10, TimeUnit.MINUTES);


        //发送邮箱
        sendPasswordEmail(tbAdmin,token);

        return ResponseResult.ok();
    }

    /**
     * 修改用户邮箱
     * @param admin
     * @return
     */
    @Override
    public ResponseResult updateEmail(TbAdmin admin) {

        if (admin == null || admin.getId() == null
                || admin.getEmail() == null || admin.getToken() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbAdmin oldAdmin = tbAdminMapper.selectByPrimaryKey(admin.getId());

        if (oldAdmin == null) {
            return  ResponseResult.build(400, "参数不合法");
        }

        if (!admin.getToken().equals(redisTemplate.boundHashOps("mywebsite-"+oldAdmin.getId()).get("re-email"))) {
            return ResponseResult.build(400, "验证码有误或已过期！");
        }

        oldAdmin.setEmail(admin.getEmail());
        tbAdminMapper.updateByPrimaryKey(oldAdmin);
        redisTemplate.boundHashOps("mywebsite-" + oldAdmin.getId()).delete("re-email");
        return ResponseResult.ok();
    }

    /**
     * 发送邮箱绑定验证邮箱
     * @param admin
     * @return
     */
    @Override
    public ResponseResult sendReEmail(TbAdmin admin) {

        if (admin == null || admin.getId() == null|| admin.getEmail() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        String token = TokenUtils.getToken();

        TbAdmin oldAdmin = tbAdminMapper.selectByPrimaryKey(admin.getId());

        if (oldAdmin == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        redisTemplate.boundHashOps("mywebsite-" + oldAdmin.getId()).put("re-email", token);
        redisTemplate.expire("mywebsite-" + oldAdmin.getId(), 10, TimeUnit.MINUTES);


        //发送邮箱
        sendReEmailTo(admin, token);

        return ResponseResult.ok();
    }

    /**
     * 发送邮箱绑定验证邮箱
     * @param admin
     * @return
     */
    private void sendReEmailTo(TbAdmin admin,String token){

        String time =  "</br>" + DateUtil.getNowTime();
        String content = admin.getAdmin() + "您好:</br>" + TOKEN_REEMAIL_CONTENT + "</br>您的验证码是：" + token + "，十分钟内有效，请在有效时间内使用。" + time;
        MailUtils.sendMail(admin.getEmail(), content, TOKEN_REEMAIL_TITLE);
    }


    /**
     * 发送密码验证邮箱
     * @param admin
     */
    private void sendPasswordEmail(TbAdmin admin,String token){

        String time =  "</br>" + DateUtil.getNowTime();
        String content = admin.getAdmin() + "您好:</br>" + TOKEN_PASSWORD_CONTENT + "</br>您的验证码是：" + token + ",十分钟内有效，请在有效时间内使用。"+ time;
        MailUtils.sendMail(admin.getEmail(), content, TOKEN_PASSWORD_TITLE);
    }

    /**
     * 发送激活邮箱
     * @param admin
     */
    private void sendTokenMail(TbAdmin admin){

        String url = TOKEN_REGISTER_URL + "active?id=" + admin.getId() + "&token=" + admin.getToken();
        String msg = "<a href='" + url + "'>点击此链接或复制链接到地址栏，访问该地址以激活账号:" +url+ "</a>";
        String time =  "</br>" + DateUtil.getNowTime();
        MailUtils.sendMail(admin.getEmail(),admin.getAdmin() + "您好:</br>"  + TOKEN_REGISTER_CONTENT + msg + time,TOKEN_REGISTER_TITLE);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        TbAdminExample example = new TbAdminExample();
        TbAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminEqualTo(s);
        List<TbAdmin> admins = tbAdminMapper.selectByExample(example);

        if (admins == null || admins.size() == 0) {
            return null;
        }

        TbAdmin tbAdmin = admins.get(0);
        List<AdminRole> roleList = new ArrayList<AdminRole>();

        AdminRole role = new AdminRole();
        roleList.add(role);

        tbAdmin.setRoleList(roleList);

        return tbAdmin;
    }
}
