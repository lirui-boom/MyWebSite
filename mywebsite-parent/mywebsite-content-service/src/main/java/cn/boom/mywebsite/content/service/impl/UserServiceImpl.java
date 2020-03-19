package cn.boom.mywebsite.content.service.impl;

import cn.boom.mywebsite.content.service.UserService;
import cn.boom.mywebsite.mapper.TbUserMapper;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.PageResult;
import cn.boom.mywebsite.pojo.TbUser;
import cn.boom.mywebsite.pojo.TbUserExample;
import cn.boom.mywebsite.utils.MailUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service(timeout = 3000)
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
    public MyWebSiteResult search(TbUser user, int pageNum, int pageSize) {

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

        return MyWebSiteResult.ok(result);
    }

    @Override
    public MyWebSiteResult add(TbUser user) {

        if (user == null || user.getName() == null || user.getNickName() == null || !MailUtils.isEmail(user.getEmail())) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        user.setPicUrl(DEFAULT_USER_IMAGE);

        tbUserMapper.insert(user);
        return MyWebSiteResult.ok(tbUserMapper.selectByPrimaryKey(user.getId()));
    }

    @Override
    public MyWebSiteResult update(TbUser user) {

        if (user == null || user.getId() == null ||user.getName() == null || user.getNickName() == null || !MailUtils.isEmail(user.getEmail())) {
            return MyWebSiteResult.build(400, "参数不合法");
        }
        tbUserMapper.updateByPrimaryKey(user);
        return MyWebSiteResult.ok();
    }

    @Override
    public MyWebSiteResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return MyWebSiteResult.build(400,"参数不合法");
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbUserMapper.deleteByPrimaryKey(id);
            }
        }
        return MyWebSiteResult.ok();
    }
}
