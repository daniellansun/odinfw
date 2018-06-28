package com.hkbea.odinfw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.hkbea"})
@MapperScan("com.hkbea.**.repositories")
public class OdinApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OdinApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OdinApplication.class, args);
    }
}
