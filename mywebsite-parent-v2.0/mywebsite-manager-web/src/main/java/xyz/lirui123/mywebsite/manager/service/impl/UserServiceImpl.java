package xyz.lirui123.mywebsite.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.manager.mapper.TbUserMapper;
import xyz.lirui123.mywebsite.pojo.TbUser;
import xyz.lirui123.mywebsite.pojo.TbUserExample;
import xyz.lirui123.mywebsite.response.PageResult;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.UserService;
import xyz.lirui123.mywebsite.utils.MailUtils;

import java.util.List;

@PropertySource(value = {"classpath:properties/DefaultPicPath.properties"}, encoding = "utf-8")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Value("${DEFAULT_USER_IMAGE}")
    private String DEFAULT_USER_IMAGE;

    @Override
    public List<TbUser> findAll() {
        return tbUserMapper.selectByExample(null);
    }

    @Override
    public TbUser findOneById(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public TbUser findOneByEmail(String email) {

        if (!MailUtils.isEmail(email)) {
            return null;
        }

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email.toLowerCase());
        List<TbUser> userList = tbUserMapper.selectByExample(example);

        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public ResponseResult search(TbUser user, int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        TbUserExample example = new TbUserExample();

        TbUserExample.Criteria criteria = example.createCriteria();

        if (user.getId() != null) {
            criteria.andIdEqualTo(user.getId());
        }

        if (user.getEmail() != null) {
            criteria.andEmailEqualTo(user.getEmail().toLowerCase());
        }

        if (user.getName() != null && !"".equals(user.getName())) {
            criteria.andNameLike("%" + user.getName() + "%");
        }

        if (user.getNickName() != null && !"".equals(user.getNickName())) {
            criteria.andNickNameLike("%" + user.getNickName() + "%");
        }

        Page page = (Page) tbUserMapper.selectByExample(example);
        PageResult result = new PageResult(page.getTotal(), page.getResult());

        return ResponseResult.ok(result);
    }

    @Override
    public ResponseResult add(TbUser user) {

        if (user == null || user.getName() == null || user.getNickName() == null || !MailUtils.isEmail(user.getEmail())) {
            return ResponseResult.build(400, "参数不合法");
        }

        user.setPicUrl(DEFAULT_USER_IMAGE);

        tbUserMapper.insert(user);
        return ResponseResult.ok(tbUserMapper.selectByPrimaryKey(user.getId()));
    }

    @Override
    public ResponseResult update(TbUser user) {

        if (user == null || user.getId() == null ||user.getName() == null || user.getNickName() == null || !MailUtils.isEmail(user.getEmail())) {
            return ResponseResult.build(400, "参数不合法");
        }
        tbUserMapper.updateByPrimaryKey(user);
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return ResponseResult.build(400,"参数不合法");
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbUserMapper.deleteByPrimaryKey(id);
            }
        }
        return ResponseResult.ok();
    }

}
