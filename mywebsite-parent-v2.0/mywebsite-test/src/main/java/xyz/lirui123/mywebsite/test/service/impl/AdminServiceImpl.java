package xyz.lirui123.mywebsite.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.lirui123.mywebsite.pojo.AdminRole;
import xyz.lirui123.mywebsite.pojo.TbAdmin;
import xyz.lirui123.mywebsite.pojo.TbAdminExample;
import xyz.lirui123.mywebsite.response.PageResult;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.test.mapper.TbAdminMapper;
import xyz.lirui123.mywebsite.test.service.AdminService;
import xyz.lirui123.mywebsite.utils.BCryptUtil;
import xyz.lirui123.mywebsite.utils.DateUtil;
import xyz.lirui123.mywebsite.utils.MailUtils;
import xyz.lirui123.mywebsite.utils.TokenUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private TbAdminMapper tbAdminMapper;


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

        System.out.println(role.getAuthority());

        tbAdmin.setRoleList(roleList);

        System.out.println(tbAdmin);

        return tbAdmin;
    }

    @Override
    public TbAdmin findOneByAdmin(String admin) {
        return null;
    }

    @Override
    public TbAdmin findOneById(Long id) {
        return null;
    }

    @Override
    public TbAdmin findOneByEmail(String email) {
        return null;
    }

    @Override
    public ResponseResult add(TbAdmin admin) {
        return null;
    }

    @Override
    public ResponseResult active(Long id, String token) {
        return null;
    }

    @Override
    public ResponseResult search(TbAdmin admin, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public ResponseResult update(TbAdmin admin) {
        return null;
    }

    @Override
    public ResponseResult delete(Long[] ids) {
        return null;
    }

    @Override
    public ResponseResult updateStatusIds(Long[] ids, String status) {
        return null;
    }

    @Override
    public ResponseResult updatePassword(TbAdmin admin) {
        return null;
    }

    @Override
    public ResponseResult sendPasswordEmail(Long id) {
        return null;
    }

    @Override
    public ResponseResult updateEmail(TbAdmin admin) {
        return null;
    }

    @Override
    public ResponseResult sendReEmail(TbAdmin admin) {
        return null;
    }
}
