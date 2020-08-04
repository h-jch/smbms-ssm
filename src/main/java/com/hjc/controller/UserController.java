package com.hjc.controller;

import com.hjc.pojo.Role;
import com.hjc.pojo.User;
import com.hjc.service.role.RoleService;
import com.hjc.service.user.UserService;
import com.hjc.tools.Constants;
import com.hjc.tools.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

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

    @RequestMapping("/jsp/user.do")
    public String userManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String method = request.getParameter("method");
        System.out.println("method==========>" + method);

        if (method != null && method.equals("query")) {
            return query(request, response, model);
        } else {
            return "error";
        }
    }

    private String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        String queryUserName = request.getParameter("queryname");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        //如果temp不为空，queryUserRole就是temp的值，否则queryUserRole为0
        int queryUserRole = 0;
        if (temp != null && !temp.equals("")) {
            queryUserRole = Integer.parseInt(temp);
        }

        int pageSize = Constants.pageSize;
        //如果pageIndex不为空，currentPageNo就是pageIndex，否则currentPageNo为1
        int currentPageNo = 1;
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        PageTool page = new PageTool();
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        int totalPageCount = page.getTotalPageCount();

        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        List<User> userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        model.addAttribute("userList", userList);

        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);
        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "userlist";
    }
}
