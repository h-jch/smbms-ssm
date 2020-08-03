package com.hjc.controller;

import com.hjc.pojo.User;
import com.hjc.service.user.UserService;
import com.hjc.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("login=====================");
        String userCode = (String) request.getAttribute("userCode");
        String userPassword = (String) request.getAttribute("userPassword");

        User user = userService.login(userCode, userPassword);
        if (user != null) {
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            return "frame";
        } else {
            request.setAttribute("error", "用户名或密码不正确");
            return "redirect:../login.jsp";
        }
    }
}
