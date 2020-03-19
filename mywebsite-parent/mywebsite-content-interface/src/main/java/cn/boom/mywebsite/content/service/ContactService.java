package cn.boom.mywebsite.content.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbContact;

public interface ContactService {

    public abstract TbContact findOneById(Long id);

    public abstract MyWebSiteResult search(TbContact contact, int pageNum, int pageSize);

    public abstract MyWebSiteResult add(TbContact contact);

    public abstract MyWebSiteResult deleteIds(Long[] ids);

    public abstract MyWebSiteResult reply(TbContact contact);
}
