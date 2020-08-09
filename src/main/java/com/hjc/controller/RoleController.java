package com.hjc.controller;

import com.alibaba.fastjson.JSON;
import com.hjc.pojo.Role;
import com.hjc.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getAjaxRoleList.do")
    public String getAjaxRoleList() {
        List<Role> roleList = roleService.getRoleList();
        return JSON.toJSONString(roleList);
    }
}
