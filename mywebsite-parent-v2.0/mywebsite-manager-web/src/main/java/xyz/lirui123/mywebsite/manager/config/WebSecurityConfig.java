package xyz.lirui123.mywebsite.manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import xyz.lirui123.mywebsite.manager.service.AdminService;
import xyz.lirui123.mywebsite.response.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //指定认证对象的来源
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
        //User.UserBuilder builder = User.builder().passwordEncoder(passwordEncoder()::encode);
        //auth.inMemoryAuthentication().withUser(builder.username("hui1").password("123").roles("ADMIN").build());
    }


    @Override
    //SpringSecurity配置信息
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html", "/item.html", "/register.html", "/registerinfo.html", "/dist/**", "/templates/**", "/templates/**", "/angularjs/**", "/admin/add", "/active", "/forgot.html", "/admin/findOneByEmail", "/admin/sendPasswordEmail", "/admin/updatePassword", "/admin/sendReEmail").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/toIndex",true)//处理器地址
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();

        http.headers().frameOptions().sameOrigin();//允许同源内嵌页面展示
    }

}
