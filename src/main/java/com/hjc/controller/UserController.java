package com.hjc.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @RequestMapping("/frame")
    public String login() {
        return "frame";
    }

    //登录
    @RequestMapping("/login.do")
    public String doLogin(HttpSession session, @RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword) {
        System.out.println("login=====================");

        User user = userService.login(userCode, userPassword);
        System.out.println(user);
        if (user != null) {
            session.setAttribute(Constants.USER_SESSION, user);
            //重定向到主页面
            return "redirect:/user/frame";
        } else {
            //设置session返回数据给页面
            session.setAttribute("error", "用户名或密码不正确");
            return "redirect:/login.jsp";
        }
    }

    //退出
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.USER_SESSION);
        System.out.println("logout============");
        return "redirect:/login.jsp";
    }

    /*@RequestMapping("/user.do")
    public String userManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String method = request.getParameter("method");
        System.out.println("method==========>" + method);

        if (method != null) {
            if (method.equals("query")) {
                return query(request, response, model);
            } else if (method.equals("pwdmodify")) {
                return matchPwd(request, response, model);
            } else if (method.equals("savepwd")) {
                return updatePwd(request, response, model);
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }*/

    @RequestMapping("/user.do")
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
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

    @RequestMapping("/useradd.do")
    public String toUserAdd(Model model) {
        //进入页面的时候显示下拉框的内容
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("userRole", roleList);
        return "useradd";
    }

    @RequestMapping("/add.do")
    public String userAdd(HttpServletRequest request, HttpSession session) {
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String date = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Integer userRole = Integer.parseInt(request.getParameter("userRole"));
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(gender);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(userRole);
        user.setCreationDate(new Date());
        User createUser = (User) session.getAttribute(Constants.USER_SESSION);
        int createById =createUser.getId();
        user.setCreatedBy(createById);
        if (userService.add(user)) {
            return "redirect:/user/user.do";
        } else {
            return "useradd";
        }
    }

    //判断要添加的userCode是否存在，返回json
    @ResponseBody
    @RequestMapping("/getUserByUserCode.do")
    public String checkUserCode(@RequestParam("userCode") String userCode) {
        if (userCode == null || userCode.equals("")) {
            return "{\"result\": \"empty\"}";
        }
        User user = userService.getUserByUserCode(userCode);
        String result;
        if (user != null) {
            result = "exist";
        } else {
            result = "notexist";
        }
        return "{\"result\": \"" + result + "\"}";
    }

    @RequestMapping("/view.do")
    public String userView(@RequestParam("uid") int id, Model model) {
        User user = userService.getUserById(id);
        int userRole = user.getUserRole();
        //设置用户角色
        if (userRole == 1) {
            user.setUserRoleName("系统管理员");
        } else if (userRole == 2) {
            user.setUserRoleName("经理");
        } else if (userRole == 3) {
            user.setUserRoleName("普通员工");
        }

        System.out.println(user);
        model.addAttribute("user", user);
        return "userview";
    }

    @RequestMapping("/modify.do")
    public String modify(@RequestParam("uid") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "usermodify";
    }

    @RequestMapping("/saveUser.do")
    public String saveUser(HttpSession session, @RequestParam("uid") int id, User user) {
        System.out.println("saveUser=====================");
        user.setId(id);
        int createId = ((User) session.getAttribute(Constants.USER_SESSION)).getId();
        user.setModifyBy(createId);
        user.setModifyDate(new Date());
        //System.out.println(user);
        if (userService.modify(user)) {
            //System.out.println("success");
            return "redirect:/user/user.do";
        } else {
            //System.out.println("fail");
            return "usermodify";
        }
    }

    //返回json
    @ResponseBody
    @RequestMapping("/delUser.do")
    public String delUser(@RequestParam("uid") int id) {
        String delResult;
        if (userService.getUserById(id) == null) {
            delResult = "notexist";
        } else if (userService.deleteUserById(id)) {
            delResult = "true";
            System.out.println("delete success");
        } else {
            delResult = "false";
            System.out.println("delete failed");
        }
        //System.out.println(delResult);
        return "{\"deResult\": \"" + delResult + "\"}";
    }

    @RequestMapping(value = "/pwdmodify", method = RequestMethod.GET)
    public String modifyPassword() {
        return "pwdmodify";
    }

    //验证旧密码，返回json
    @ResponseBody
    @RequestMapping(value = "/matchPwd.do")
    public String matchPwd(HttpSession session, @RequestParam(value = "oldpassword", required = false) String oldPassword) {
        System.out.println("matchPwd======================");
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        String password = user.getUserPassword();
        String result;
        if (user == null) {
            result = "sessionerror";
        } else if (oldPassword == null || oldPassword.equals("")) {
            result = "error";
        } else if (oldPassword.equals(password)) {
            result = "true";
        } else {
            result = "false";
        }
        return "{\"result\": \"" + result + "\"}";
    }

    //更新密码
    @RequestMapping(value = "/updatePwd.do", method = RequestMethod.POST)
    public String updatePwd(HttpSession session, @RequestParam("newpassword") String newPassword, Model model) {
        System.out.println("updatePwd==================");
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        if (user != null && newPassword != null && !newPassword.equals("")) {
            if (userService.updatePwd(user.getId(), newPassword)) {
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码成功，请重新登陆！");
                session.removeAttribute(Constants.USER_SESSION);
            } else {
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        } else {
            model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        return "pwdmodify";
    }
}
