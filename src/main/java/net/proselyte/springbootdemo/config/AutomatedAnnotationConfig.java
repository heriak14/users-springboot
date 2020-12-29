package net.proselyte.springbootdemo.config;

import net.proselyte.springbootdemo.service.JsonUserService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomatedAnnotationConfig {

    @Bean
    public UserService userService() {
        return new JsonUserService();
    }
}
