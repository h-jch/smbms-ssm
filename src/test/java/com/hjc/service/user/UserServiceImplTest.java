package com.hjc.service.user;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImplTest {

    @Test
    public void testLogin() {
        String userCode = "admin";
        String userPassword = "1234567";
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        System.out.println(userService.login(userCode, userPassword));
    }
}
