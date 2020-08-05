package com.hjc.service.user;

import com.hjc.pojo.User;
import com.hjc.tools.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserServiceTest {

    private ApplicationContext context;
    private UserService userService;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean("userService", UserServiceImpl.class);
    }

    @Test
    public void testLogin() {
        String userCode = "admin";
        String userPassword = "1234567";
        System.out.println(userService.login(userCode, userPassword));
    }

    @Test
    public void testGetUserCount() {
        String userName = null;
        int userRole = 0;
        System.out.println(userService.getUserCount(userName, userRole));
    }

    @Test
    public void testGetUserList() {
        String userName = null;
        int userRole = 0;
        int currentPageNo = 2;
        int pageSize = Constants.pageSize;
        List<User> userList = userService.getUserList(userName, userRole, currentPageNo, pageSize);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdatePwd() {
        int id = 1;
        String pwd = "1111111";
        System.out.println(userService.updatePwd(id, pwd));
    }
}
