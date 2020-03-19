package cn.boom.mywebsite.content.service.impl;

import cn.boom.mywebsite.content.service.PersonService;
import cn.boom.mywebsite.mapper.TbPersonalMapper;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbPersonal;
import cn.boom.mywebsite.pojo.TbPersonalExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service(timeout = 3000)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private TbPersonalMapper tbPersonalMapper;

    /**
     * 查询个人信息
     * @param id
     * @return
     */
    @Override
    public TbPersonal findById(Long id) {

        if (id == null) {
            return null;
        }
        return tbPersonalMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询个人信息
     * @param name
     * @return
     */
    @Override
    public TbPersonal findByName(String name) {

        if (name == null || "".equals(name)) {
            return null;
        }

        TbPersonalExample example = new TbPersonalExample();
        TbPersonalExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<TbPersonal> personalList = tbPersonalMapper.selectByExample(example);

        if (personalList != null && personalList.size() > 0) {
            return personalList.get(0);
        }

        return null;
    }

    /**
     * 更新个人信息
     * @param tbPersonal
     * @return
     */
    @Override
    public MyWebSiteResult update(TbPersonal tbPersonal) {

        if (tbPersonal == null || tbPersonal.getId() == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        tbPersonalMapper.updateByPrimaryKey(tbPersonal);

        return MyWebSiteResult.ok();
    }

}
