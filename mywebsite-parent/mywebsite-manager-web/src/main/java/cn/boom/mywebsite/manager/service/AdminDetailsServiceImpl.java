package cn.boom.mywebsite.manager.service;
import cn.boom.mywebsite.content.service.AdminService;
import cn.boom.mywebsite.pojo.TbAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        TbAdmin admin = adminService.findOneByAdmin(username);

        if (admin != null && admin.getStatus().equals("1")) {
            return new User(username, admin.getPassword(), authorities);
        }

        return null;
    }

}
