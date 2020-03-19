package cn.boom.mywebsite.manager.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json; charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(MyWebSiteResult.ok("登陆成功"));
        httpServletResponse.getWriter().write(json);

    }
}
