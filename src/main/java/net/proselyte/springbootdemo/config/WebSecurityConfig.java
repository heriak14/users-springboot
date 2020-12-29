package net.proselyte.springbootdemo.config;

import net.proselyte.springbootdemo.service.JpaUserService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/vendor/**")
                .permitAll();
        http
                .authorizeRequests()
                .antMatchers("/user-create").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/users")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        final UserDetails[] users = userService.findAll().stream()
                .map(this::buildUserDetails)
                .toArray(UserDetails[]::new);
        return new InMemoryUserDetailsManager(users);
    }

    private UserDetails buildUserDetails(net.proselyte.springbootdemo.model.User user) {
        return User.withDefaultPasswordEncoder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}