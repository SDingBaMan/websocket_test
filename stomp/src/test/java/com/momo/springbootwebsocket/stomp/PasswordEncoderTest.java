package com.momo.springbootwebsocket.stomp;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: MQG
 * @date: 2018/11/9
 */
public class PasswordEncoderTest {
    
    @Test
    public void testEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("user1"));
        System.out.println(passwordEncoder.encode("user2"));
        System.out.println(passwordEncoder.encode("user3"));
    }
}
