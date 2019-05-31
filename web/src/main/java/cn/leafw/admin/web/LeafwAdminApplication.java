package cn.leafw.admin.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "cn.leafw.admin.*")
@MapperScan("cn.leafw.admin.dao.mapper")
public class LeafwAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeafwAdminApplication.class, args);
    }

}
