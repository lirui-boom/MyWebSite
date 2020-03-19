package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.content.service.AdminService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbAdmin;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Reference
    private AdminService adminService;

    /**
     * 注册
     * @param admin
     * @return
     */
    @RequestMapping("/admin/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbAdmin admin) {
        return adminService.add(admin);
    }

    /**
     * 激活
     * @param id
     * @param token
     * @return
     */
    @RequestMapping("/active")
    @ResponseBody
    public ModelAndView active(Long id, String token) {

        MyWebSiteResult result = adminService.active(id, token);
        ModelAndView mav = new ModelAndView();
        if (result.getStatus() == 200) {
            mav.setViewName("active");
        }else {
            mav.setViewName("error");
        }
        return mav;
    }

    /**
     * 获取登录信息
     * @return
     */
    @RequestMapping("/admin/getLoginInfo")
    @ResponseBody
    public MyWebSiteResult getLoginInfo(){
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        TbAdmin admin = adminService.findOneByAdmin(loginName);
        admin.setToken(null);
        return MyWebSiteResult.ok(admin);
    }

    /**
     * 分页条件搜索
     * @param admin
     * @param rows
     * @param page
     * @return
     */
    @RequestMapping("/admin/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbAdmin admin, int rows, int page) {
        return adminService.search(admin, rows, page);
    }

    /**
     * 根据Email查询用户
     * @param email
     * @return
     */
    @RequestMapping("/admin/findOneByEmail")
    @ResponseBody
    public MyWebSiteResult findOneByEmail(String email) {

        if (email == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        TbAdmin admin = adminService.findOneByEmail(email);
        if (admin == null) {
            return MyWebSiteResult.build(400, "未查询到此用户");
        }
        admin.setToken(null);
        return MyWebSiteResult.ok(admin);
    }


    /**
     * 修改用户
     * @param admin
     * @return
     */
    @RequestMapping("/admin/update")
    @ResponseBody
    public MyWebSiteResult update(@RequestBody TbAdmin admin) {
        return adminService.update(admin);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/admin/deleteIds")
    @ResponseBody
    public MyWebSiteResult deleteIds(Long[] ids){
        return adminService.delete(ids);
    }

    /**
     * 审核用户
     * @param ids
     * @return
     */
    @RequestMapping("/admin/updateStatusIds")
    @ResponseBody
    public MyWebSiteResult updateStatusIds(Long[] ids,String status){
        return adminService.updateStatusIds(ids,status);
    }


    /**
     * 修改用户密码
     * @param admin
     * @return
     */
    @RequestMapping("/admin/updatePassword")
    @ResponseBody
    public MyWebSiteResult updatePassword(@RequestBody TbAdmin admin) {
        return adminService.updatePassword(admin);
    }

    /**
     * 发送密码验证邮箱
     * @param id
     * @return
     */
    @RequestMapping("/admin/sendPasswordEmail")
    @ResponseBody
    public MyWebSiteResult sendPasswordEmail(Long id){
        return adminService.sendPasswordEmail(id);
    }


    /**
     * 修改用户邮箱地址
     * @param admin
     * @return
     */
    @RequestMapping("/admin/updateEmail")
    @ResponseBody
    public MyWebSiteResult updateEmail(@RequestBody TbAdmin admin) {
        return adminService.updateEmail(admin);
    }

    /**
     * 发送邮箱绑定验证邮箱
     * @param admin
     * @return
     */
    @RequestMapping("/admin/sendReEmail")
    @ResponseBody
    public MyWebSiteResult sendReEmail(@RequestBody TbAdmin admin){
        return adminService.sendReEmail(admin);
    }



}
