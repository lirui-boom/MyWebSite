package xyz.lirui123.mywebsite.protal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"xyz.lirui123.mywebsite.protal.mapper"})
public class MyWebSiteProtalApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyWebSiteProtalApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyWebSiteProtalApplication.class, args);
    }
}
