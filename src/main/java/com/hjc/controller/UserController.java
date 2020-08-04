package com.hjc.controller;

import com.hjc.pojo.User;
import com.hjc.service.user.UserService;
import com.hjc.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("jsp/frame")
    public String login() {
        return "frame";
    }

    @RequestMapping("/login.do")
    public String doLogin(HttpServletRequest request) {
        System.out.println("login=====================");
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");

        User user = userService.login(userCode, userPassword);
        System.out.println(user);
        if (user != null) {
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            //重定向到主页面
            return "redirect:/jsp/frame";
        } else {
            //设置session返回数据给页面
            request.getSession().setAttribute("error", "用户名或密码不正确");
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/jsp/logout.do")
     public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.USER_SESSION);
        System.out.println("logout============");
        return "redirect:/login.jsp";
    }
}
