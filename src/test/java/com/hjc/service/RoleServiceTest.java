package com.hjc.service;

import com.hjc.pojo.Role;
import com.hjc.service.role.RoleService;
import com.hjc.service.role.RoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class RoleServiceTest {

    private ApplicationContext context;
    private RoleService roleService;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        roleService = context.getBean("roleService", RoleServiceImpl.class);
    }

    @Test
    public void testGetRoleList() {
        List<Role> roleList = roleService.getRoleList();
        for (Role role : roleList) {
            System.out.println(role);
        }
    }
}
