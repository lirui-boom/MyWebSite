package xyz.lirui123.mywebsite.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.manager.mapper.TbPersonalMapper;
import xyz.lirui123.mywebsite.pojo.TbPersonal;
import xyz.lirui123.mywebsite.pojo.TbPersonalExample;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.manager.service.PersonService;

import java.util.List;

@Service
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
    public ResponseResult update(TbPersonal tbPersonal) {

        if (tbPersonal == null || tbPersonal.getId() == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        tbPersonalMapper.updateByPrimaryKey(tbPersonal);

        return ResponseResult.ok();
    }

}
