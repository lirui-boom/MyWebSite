package cn.boom.mywebsite.manager.service;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json; charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(MyWebSiteResult.build(400, "用户名或密码错误"));
        httpServletResponse.getWriter().write(json);

    }
}
