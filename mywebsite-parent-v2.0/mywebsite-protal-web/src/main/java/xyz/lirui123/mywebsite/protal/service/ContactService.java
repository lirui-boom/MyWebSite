package xyz.lirui123.mywebsite.protal.service;


import xyz.lirui123.mywebsite.pojo.TbContact;
import xyz.lirui123.mywebsite.response.ResponseResult;

public interface ContactService {

    public abstract TbContact findOneById(Long id);

    public abstract ResponseResult search(TbContact contact, int pageNum, int pageSize);

    public abstract ResponseResult add(TbContact contact);

    public abstract ResponseResult deleteIds(Long[] ids);

    public abstract ResponseResult reply(TbContact contact);
}
