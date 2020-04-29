package xyz.lirui123.mywebsite.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.lirui123.mywebsite.test.service.AdminService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   // @Autowired
    //private AdminService adminService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //指定认证对象的来源
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
        User.UserBuilder builder = User.builder().passwordEncoder(passwordEncoder()::encode);
        auth.inMemoryAuthentication().withUser(builder.username("hui1").password("123").roles("ADMIN").build());
    }

    /*
    @Override
    //SpringSecurity配置信息
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successForwardUrl("/admin/index.html")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }*/
    @Override
    //SpringSecurity配置信息
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.jsp", "/failer.jsp", "/css/**", "/image/**", "/plugins/**").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/toIndex",true)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login.jsp").invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }
}
