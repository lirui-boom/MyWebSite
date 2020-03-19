package cn.boom.mywebsite.content.service.impl;

import cn.boom.mywebsite.content.service.ContactService;
import cn.boom.mywebsite.mapper.TbContactMapper;
import cn.boom.mywebsite.mapper.TbUserMapper;
import cn.boom.mywebsite.pojo.*;
import cn.boom.mywebsite.utils.DateUtil;
import cn.boom.mywebsite.utils.MailUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service(timeout = 8000)
public class ContactServiceImpl implements ContactService {

    @Autowired
    private TbContactMapper tbContactMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Value("${REPLY_TITLE}")
    private String REPLY_TITLE;
    @Value("${REPLY_CONTENT}")
    private String REPLY_CONTENT;

    @Override
    public TbContact findOneById(Long id) {
        return tbContactMapper.selectByPrimaryKey(id);
    }

    @Override
    public MyWebSiteResult search(TbContact contact, int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        TbContactExample example = new TbContactExample();
        TbContactExample.Criteria criteria = example.createCriteria();

        if (contact.getId() != null) {
            criteria.andIdEqualTo(contact.getId());
        }

        if (contact.getStatus() != null && !"".equals(contact.getStatus())) {
            criteria.andStatusEqualTo(contact.getStatus());
        }

        if (contact.getUid() != null) {
            criteria.andUidEqualTo(contact.getUid());
        }

        Page page = (Page) tbContactMapper.selectByExample(example);
        PageResult result = new PageResult(page.getTotal(), page.getResult());

        return MyWebSiteResult.ok(result);
    }

    @Override
    public MyWebSiteResult add(TbContact contact) {

        if (contact == null || contact.getUid() == null || contact.getMessage() == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        contact.setStatus("0");
        contact.setCreated(new Date());
        tbContactMapper.insert(contact);

        return MyWebSiteResult.ok();
    }

    @Override
    public MyWebSiteResult deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbContactMapper.deleteByPrimaryKey(id);
            }
        }
        return MyWebSiteResult.ok();
    }

    @Override
    public MyWebSiteResult reply(TbContact contact) {

        if (contact == null || contact.getId() == null || contact.getMessage() == null
                || contact.getUid() == null || contact.getStatus() == null || contact.getReplyMsg() == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        contact.setReplied(new Date());
        contact.setStatus("1");
        tbContactMapper.updateByPrimaryKey(contact);

        sendReplyEmail(tbUserMapper.selectByPrimaryKey(contact.getUid()),contact.getReplyMsg());

        return MyWebSiteResult.ok();
    }

    /***
     * 发送回复邮箱信息
     * @param user
     */
    private void sendReplyEmail(TbUser user,String replyMsg) {

        String time =  "</br>" + DateUtil.getNowTime();
        String content = "亲爱的" + user.getName() + ", 您好:</br>" + replyMsg + REPLY_CONTENT + time;
        MailUtils.sendMail(user.getEmail(), content, REPLY_TITLE);
    }
}
