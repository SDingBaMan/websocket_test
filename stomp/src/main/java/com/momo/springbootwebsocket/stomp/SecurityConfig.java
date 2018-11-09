package com.momo.springbootwebsocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: MQG
 * @date: 2018/11/9
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // 定义3个可以登录的内存用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码加密器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加入3个内存用户，
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("user1")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .and()
                .withUser("user2")
                .password(passwordEncoder.encode("user2"))
                .roles("ADMIN")
                .and()
                .withUser("user3")
                .password(passwordEncoder.encode("user3"))
                .roles("USER");
    }
}
